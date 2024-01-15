package edu.school21.ORM.annotations;


import jakarta.persistence.GenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrmColumnId {
    GenerationType id() default GenerationType.AUTO;
}
