package com.nicholaspilotto.bookservice.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Allow request to everyone.
 */
@Retention(RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AllowAnonymous {
}
