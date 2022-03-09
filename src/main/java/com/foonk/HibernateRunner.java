package com.foonk;

import com.foonk.entity.Company;
import com.foonk.entity.User;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            var user = session.get(User.class, 1L);
//            System.out.println(user.getPayments().size());
//            System.out.println(user.getCompany().getName());
            var users = session.createQuery("select u from User u", User.class)
                    .list();

            session.getTransaction().commit();
        }
    }}

