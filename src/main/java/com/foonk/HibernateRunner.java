package com.foonk;

import com.foonk.converter.BirthdayConverter;
import com.foonk.entitiy.Birthday;
import com.foonk.entitiy.Role;
import com.foonk.entitiy.User;
import com.foonk.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingDeque;

public class HibernateRunner {
    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .username("petr@yahoo.com")
                .firstname("Petr")
                .lastname("Petrov")
                .build();
        try (var sessionFactory = HibernateUtil.buildSessionFactory()){
            try (var session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }
            try (var session2 = sessionFactory.openSession()) {
                session2.beginTransaction();
                user.setFirstname("Vasya");
                session2.merge(user);

                session2.getTransaction().commit();
            }
        }
    }
}
