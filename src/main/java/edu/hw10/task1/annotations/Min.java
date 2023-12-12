package edu.hw10.task1.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * The {@code Min} annotation is used to mark method parameters with a lower limit constraint.
 * It indicates that the annotated parameter should have a value not less than the specified minimum value.
 * This annotation is applicable to parameters only.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(PARAMETER)
public @interface Min {
    /**
     * The minimum value that the annotated parameter is allowed to have.
     *
     * @return the minimum value allowed for the annotated parameter.
     */
    int value();
}
