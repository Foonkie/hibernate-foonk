package com.foonk.util;

import com.foonk.converter.BirthdayConverter;
import com.foonk.entitiy.Company;
import com.foonk.entitiy.Profile;
import com.foonk.entitiy.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
   public static SessionFactory buildSessionFactory(){
    var configuration = new Configuration();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
       configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(User .class);
       configuration.addAnnotatedClass(Profile.class);
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();
        return configuration.buildSessionFactory();
}}

