package com.foonk;

import com.foonk.dao.PaymentRepository;
import com.foonk.dao.UserRepository;
import com.foonk.entity.Payment;
import com.foonk.entity.User;
import com.foonk.interceptor.GlobalInterceptor;
import com.foonk.mapper.CompanyReadMapper;
import com.foonk.mapper.UserReadMapper;
import com.foonk.service.UserService;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userRepository = new UserRepository(session);
            var paymentRepository = new PaymentRepository(session);
            var userService = new UserService(userRepository, userReadMapper);
                userService.findById(1L).ifPresent(System.out::println);


            session.getTransaction().commit();
        }
    }


}