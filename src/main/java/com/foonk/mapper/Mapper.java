package com.foonk.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
