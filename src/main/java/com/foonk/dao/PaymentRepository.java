package com.foonk.dao;

import com.foonk.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentRepository extends RepositoryBase<Long, Payment> {
public PaymentRepository(SessionFactory sessionFactory) {
    super(sessionFactory, Payment.class);
}
}

