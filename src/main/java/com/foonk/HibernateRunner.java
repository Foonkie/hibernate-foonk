package com.foonk;

import com.foonk.dao.CompanyRepository;
import com.foonk.dao.PaymentRepository;
import com.foonk.dao.UserRepository;
import com.foonk.dto.UserCreateDto;
import com.foonk.entity.Payment;
import com.foonk.entity.PersonalInfo;
import com.foonk.entity.Role;
import com.foonk.entity.User;
import com.foonk.interceptor.GlobalInterceptor;
import com.foonk.interceptor.TransactionInterceptor;
import com.foonk.mapper.CompanyReadMapper;
import com.foonk.mapper.UserCreateMapper;
import com.foonk.mapper.UserReadMapper;
import com.foonk.service.UserService;
import com.foonk.util.HibernateUtil;
import com.foonk.util.TestDataImporter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
//            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userRepository = new UserRepository(session);

            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var paymentRepository = new PaymentRepository(session);
//            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);
            var transactionInterceptor = new TransactionInterceptor(sessionFactory);
            var userService = new ByteBuddy()
                    .subclass(UserService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(UserService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(UserRepository.class, UserReadMapper.class, UserCreateMapper.class)
                    .newInstance(userRepository, userReadMapper, userCreateMapper);
                userService.findById(1L).ifPresent(System.out::println);
            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Bill")
                            .lastname("Clinton")
                            .birthDate(LocalDate.now())
                            .build(),
                    "bill2@gmail.com",
                    null,
                    Role.USER,
                    1
            );
            userService.create(userCreateDto);


//            session.getTransaction().commit();
        }
    }


}