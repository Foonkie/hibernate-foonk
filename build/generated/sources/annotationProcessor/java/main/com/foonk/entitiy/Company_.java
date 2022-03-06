package com.foonk.entitiy;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile ListAttribute<Company, LocaleInfo> locales;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile SingularAttribute<Company, Integer> id;
	public static volatile SetAttribute<Company, User> users;

	public static final String LOCALES = "locales";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USERS = "users";

}

