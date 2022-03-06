package com.foonk.entitiy;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, PersonalInfo> personalInfo;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile SingularAttribute<User, Profile> profile;
	public static volatile SetAttribute<User, Chat> chats;
	public static volatile SingularAttribute<User, Company> company;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> info;

	public static final String PERSONAL_INFO = "personalInfo";
	public static final String ROLE = "role";
	public static final String PROFILE = "profile";
	public static final String CHATS = "chats";
	public static final String COMPANY = "company";
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String INFO = "info";

}

