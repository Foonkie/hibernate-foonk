package com.foonk.entitiy;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chat.class)
public abstract class Chat_ {

	public static volatile SingularAttribute<Chat, String> name;
	public static volatile SingularAttribute<Chat, Long> id;
	public static volatile SetAttribute<Chat, User> users;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USERS = "users";

}

