package edu.hw10.task2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The {@code Cache} annotation is used to mark methods for caching purposes. It can be applied
 * to methods to indicate whether the results should be cached and persisted.
 * <p>
 * By default, the caching behavior is disabled ({@code persist} is set to {@code false}).
 * To enable caching and specify whether the cache should be persisted, you can use this annotation
 * with the desired persist value.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * Specifies whether the cache should persist its contents.
     * The default value is false, indicating that the cache is not persisted.
     *
     * @return {@code true} if the cache should persist its contents, {@code false} otherwise.
     */
    boolean persist() default false;
}
