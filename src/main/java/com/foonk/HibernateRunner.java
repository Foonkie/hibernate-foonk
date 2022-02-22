package com.foonk;

import com.foonk.converter.BirthdayConverter;
import com.foonk.entitiy.Birthday;
import com.foonk.entitiy.Role;
import com.foonk.entitiy.User;
import com.foonk.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingDeque;
@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .username("petr@yahoo.com")
                .firstname("Petr")
                .lastname("Petrov")
                .build();
        log.info("User entity is in transient state, object: {}", user);
        try (var sessionFactory = HibernateUtil.buildSessionFactory()){
            var session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created, {}", transaction);
                session1.saveOrUpdate(user);
                log.trace("User is in persistent state: {}, session {}", user, session1);
                session1.getTransaction().commit();
                log.warn("User is in detached state: {}, session is closed {}", user, session1);
            } catch (Exception exception) {
                log.error("Exception occurred", exception);
                throw exception;
        }
    }
}
}
