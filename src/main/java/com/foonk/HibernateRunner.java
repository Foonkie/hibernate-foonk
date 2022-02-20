package com.foonk;

import com.foonk.converter.BirthdayConverter;
import com.foonk.entitiy.Birthday;
import com.foonk.entitiy.Role;
import com.foonk.entitiy.User;
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
//        BlockingDeque<Connection> pool=null;
//        var connection = DriverManager.getConnection("db.url", "db.username", "db.password");
        var configuration = new Configuration();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        configuration.addAnnotatedClass(User.class);
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
        var session = sessionFactory.openSession()) {
            session.beginTransaction();
            User build = User.builder()
                    .username("hibernate@gmail.com")
                    .firstname("Vasya")
                    .lastname("Shelkov")
                    .info("""
                  {
                  "name": "Petr",
                  "id": "67"
                  }
                  """ )
                    .birthDate(new Birthday(LocalDate.of(2000, 05, 23)))
                    .role(Role.ADMIN)
                    .build();
            session.save(build);
            session.getTransaction().commit();
        }
    }
}
