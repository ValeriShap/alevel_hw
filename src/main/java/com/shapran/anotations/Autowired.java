package com.shapran.anotations;

import com.shapran.repository.Crud;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.CONSTRUCTOR)
@Retention(value = RetentionPolicy.RUNTIME)

public @interface Autowired {
    Class<? extends Crud> CrudRepossitory();
}
