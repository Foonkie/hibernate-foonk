package com.foonk;


import com.foonk.entitiy.*;
import com.foonk.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    void checkH2() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var google = Company.builder()
                    .name("Google")
                    .build();
            session.save(google);

            Programmer programmer = Programmer.builder()
                    .username("ivan@gmail.com")
                    .language(Language.JAVA)
                    .company(google)
                    .build();
            session.save(programmer);

            Manager manager = Manager.builder()
                    .username("sveta@gmail.com")
                    .projectName("Starter")
                    .company(google)
                    .build();
            session.save(manager);
            session.flush();

            session.clear();

            var programmer1 = session.get(Programmer.class, 1L);
            var manager1 = session.get(User.class, 1L);
            System.out.println();


            session.getTransaction().commit();
        }
    }
    @Test
    void localeInfo() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var company = session.get(Company.class, 1);
            company.getLocales().add(LocaleInfo.of("ru", "Описание на русском"));
            company.getLocales().add(LocaleInfo.of("en", "English description"));
//            System.out.println(company.getLocales());

            session.getTransaction().commit();
        }
    }
    @Test
    void checkOneToOne() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();


//            var user = User.builder()
//                    .username("test4@gmail.com")
//                    .build();
            var profile = Profile.builder()
                    .language("ru")
                    .street("Kolasa 18")
                    .build();
//            profile.setUser(user);

//
//            session.save(user);
//            profile.setUser(user);
//            session.save(profile);

            session.getTransaction().commit();
        }
    }
    @Test
    void checkManyToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, 1L);

//            user.getChats().clear();

            var chat = Chat.builder()
                    .name("Petya")
                    .build();
            user.addChat(chat);

            session.save(chat);

            session.getTransaction().commit();
        }
    }
    @Test
    void addUserToNewCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Facebook")
                .build();

//        var user = User.builder()
//                .username("sveta@gmail.com")
//                .build();
////        user.setCompany(company);
////        company.getUsers().add(user)
//        company.addUser(user);


        session.save(company);

        session.getTransaction().commit();
    }
    @Test
    void checkReflectionApi() throws SQLException, IllegalAccessException {
        User user = null;

        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        String tableName = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));

        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            preparedStatement.setObject(1, declaredField.get(user));
        }
    }










}