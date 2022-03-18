package com.foonk.dao;

import com.foonk.entity.Company;
import com.foonk.entity.User;
import lombok.Value;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class UserRepository extends RepositoryBase<Long, User> {
    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}

