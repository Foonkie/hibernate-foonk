package com.foonk;

import com.foonk.entity.Payment;
import com.foonk.entity.User;
import com.foonk.interceptor.GlobalInterceptor;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            TestDataImporter.importData(sessionFactory);
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();
                User user=null;
                user = session.find(User.class, 1L);
                user.getCompany().getName();
                var user1 = session.find(User.class, 1L);

                session.getTransaction().commit();
            }
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();

                var user2 = session.find(User.class, 1L);
                user2.getCompany().getName();
                session.getTransaction().commit();
            }
        }
    }
}