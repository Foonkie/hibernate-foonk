package com.foonk;

import com.foonk.dao.PaymentRepository;
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
            try (var session = sessionFactory.openSession()) {
                session.beginTransaction();

                var paymentRepository = new PaymentRepository(sessionFactory);

                paymentRepository.findById(1L).ifPresent(System.out::println);

                session.getTransaction().commit();
            }
        }
    }
}