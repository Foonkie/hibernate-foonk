package com.foonk.dao;

import com.foonk.entity.Company;
import org.hibernate.SessionFactory;

public class CompanyRepository extends RepositoryBase<Integer, Company> {
public CompanyRepository(SessionFactory sessionFactory) {
    super(sessionFactory, Company.class);
}
}

