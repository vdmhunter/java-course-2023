package edu.hw10.task1.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * The {@code NotNull} annotation is used to mark method parameters that should not be {@code null}.
 * It indicates that the annotated parameter must have a non-null value.
 * This annotation is applicable to parameters only.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(PARAMETER)
public @interface NotNull {
}
