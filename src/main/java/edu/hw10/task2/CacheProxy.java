package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code CacheProxy} class is a dynamic proxy that intercepts method calls and provides caching functionality
 * based on the presence of the {@link Cache} annotation. It allows for in-memory and persistent caching of method
 * results.
 */
public final class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> inMemoryCache;
    private final FileHashMap persistentCache;

    private CacheProxy(Object target, String path) {
        this.target = target;
        this.inMemoryCache = new HashMap<>();
        this.persistentCache = new FileHashMap(path);
    }

    /**
     * Creates a dynamic proxy for the given target object, implementing the specified interface class,
     * and enabling caching functionality based on the presence of the {@link Cache} annotation.
     *
     * @param target         The target object to be proxied.
     * @param interfaceClass The interface class that the proxy should implement.
     * @param path           The path to the file used for persistent caching (ignored if persistent caching
     *                       is disabled).
     * @param <T>            The type of the interface implemented by the proxy.
     * @return A dynamic proxy instance that implements the specified interface and provides caching functionality.
     */
    @SuppressWarnings("unchecked")
    public static <T> @NotNull T create(T target, @NotNull Class<?> interfaceClass, String path) {
        return (T) Proxy.newProxyInstance(
            interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass},
            new CacheProxy(target, path)
        );
    }

    /**
     * Handles the invocation of methods on the proxied object. It intercepts method calls, checks for the presence
     * of the {@link Cache} annotation, and performs caching based on the annotation configuration.
     *
     * @param proxy  The proxy instance on which the method was invoked.
     * @param method The method being invoked.
     * @param args   The arguments passed to the method.
     * @return The result of the method invocation, either retrieved from the cache or by invoking the target method.
     * @throws Throwable If an error occurs during the method invocation.
     */
    @Override
    public Object invoke(Object proxy, @NotNull Method method, Object[] args) throws Throwable {
        boolean isPersist = false;

        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            isPersist = cacheAnnotation.persist();
        }

        String key = generateKey(method, args);

        if (!isPersist && inMemoryCache.containsKey(key)) {
            return inMemoryCache.get(key);
        } else if (isPersist && persistentCache.containsKey(key)) {
            return persistentCache.get(key);
        }

        Object result = method.invoke(target, args);

        if (isPersist) {
            persistentCache.put(key, result);
        } else {
            inMemoryCache.put(key, result);
        }

        return result;
    }

    /**
     * Generates a unique key for caching based on the method name and its arguments.
     *
     * @param method The method for which the key is generated.
     * @param args   The arguments passed to the method.
     * @return A unique key representing the method and its arguments for caching purposes.
     */
    private @NotNull String generateKey(@NotNull Method method, Object[] args) {
        return method.getName() + Arrays.toString(args);
    }
}
