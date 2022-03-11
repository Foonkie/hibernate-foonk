package com.foonk;

import com.foonk.entity.*;
import com.foonk.entity.UserChat;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.graph.GraphSemantic;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            try {
            Transaction transaction = session.beginTransaction();
            var payment1 = session.find(Payment.class, 1L);
            var payment2 = session.find(Payment.class, 2L);
            session.getTransaction().commit();}
            catch (Exception exception){
                session.getTransaction().rollback();
                throw exception;
            }
        }
    }
}
