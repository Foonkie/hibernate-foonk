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

import javax.persistence.LockModeType;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session1 = sessionFactory.openSession())
        {


            session.beginTransaction();
            session1.beginTransaction();
            Payment payment = session.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            payment.setAmount(payment.getAmount()+10);
            Payment theSamePayment = session1.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            theSamePayment.setAmount(theSamePayment.getAmount()+20);
            session.getTransaction().commit();


        }
    }
}
