package com.foonk.util;

import com.foonk.converter.BirthdayConverter;
import com.foonk.entitiy.*;
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
       configuration.addAnnotatedClass(Chat.class);
       configuration.addAnnotatedClass(Profile.class);
       configuration.addAnnotatedClass(Programmer.class);
       configuration.addAnnotatedClass(Manager.class);
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();
        return configuration.buildSessionFactory();
}}

