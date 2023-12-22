package edu.hw10.task1;

import edu.common.Generated;
import edu.hw10.task1.annotations.NotNull;
import edu.hw10.task1.generators.IntegerRandomValueGenerator;
import edu.hw10.task1.generators.RandomValueGenerator;
import edu.hw10.task1.generators.StringRandomValueGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code RandomObjectGenerator} class facilitates the creation of random instances of specified classes
 * using reflection and generation of random values for their parameters.
 */
public class RandomObjectGenerator {
    private final Map<String, RandomValueGenerator<?>> randomValueGenerators = new HashMap<>();
    private static final String EXCEPTION_MSG = "No suitable constructor or factory method found for class ";

    /**
     * Constructs a {@code RandomObjectGenerator} and initializes default random value generators for primitive types.
     */
    public RandomObjectGenerator() {
        randomValueGenerators.put("int", new IntegerRandomValueGenerator());
        randomValueGenerators.put("java.lang.String", new StringRandomValueGenerator());
    }

    /**
     * Generates a random instance of the specified class using its constructor or factory method.
     *
     * @param clazz       The class for which a random instance needs to be generated. Must not be {@code null}.
     * @param methodNames Optional factory method names to be considered in addition to the constructors.
     * @param <T>         The type of the class for which an instance is generated.
     * @return A randomly generated instance of the specified class.
     * @throws InvocationTargetException If the underlying constructor or factory method throws an exception.
     * @throws InstantiationException    If the specified class is an abstract class or an interface,
     *                                   or if there is no suitable constructor or factory method.
     * @throws IllegalAccessException    If the constructor or factory method is inaccessible.
     */
    @SuppressWarnings("unchecked")
    public <T> T nextObject(Class<T> clazz, String... methodNames)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = findSuitableConstructor(clazz);

        if (constructor != null) {
            //noinspection unchecked
            return (T) constructInstance(constructor);
        }

        for (String methodName : methodNames) {
            Method method = findFactoryMethod(clazz, methodName);
            if (method != null) {
                //noinspection unchecked
                return (T) constructInstance(method);
            }
        }

        throw new InstantiationException(EXCEPTION_MSG + clazz.getName());
    }

    /**
     * Finds the first public constructor of the specified class.
     *
     * @param clazz The class for which a constructor needs to be found. Must not be {@code null}.
     * @return The first public constructor of the specified class, or {@code null} if none is found.
     */
    @Generated
    private @Nullable Constructor<?> findSuitableConstructor(@org.jetbrains.annotations.NotNull Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> constructor : constructors) {
            if (Modifier.isPublic(constructor.getModifiers())) {
                return constructor;
            }
        }

        return null;
    }

    /**
     * Finds a public static factory method with the specified name in the specified class.
     *
     * @param clazz      The class for which a factory method needs to be found. Must not be {@code null}.
     * @param methodName The name of the factory method to be found.
     * @return The factory method with the specified name, or {@code null} if none is found.
     */
    @Generated
    private @Nullable Method findFactoryMethod(@org.jetbrains.annotations.NotNull Class<?> clazz, String methodName) {
        for (Method method : clazz.getMethods()) {
            if (isStaticPublicMethodWithName(methodName, method)) {
                return method;
            }
        }

        return null;
    }

    /**
     * Checks if a method is public, static, and has a specific name.
     *
     * @param methodName The name to check against.
     * @param method     The method to be checked.
     * @return {@code true} if the method is public, static, and has the specified name; otherwise, {@code false}.
     */
    @Generated
    private static boolean isStaticPublicMethodWithName(
        String methodName,
        @org.jetbrains.annotations.NotNull Method method
    ) {
        return method.getName().equals(methodName) && Modifier.isStatic(method.getModifiers())
            && Modifier.isPublic(method.getModifiers());
    }

    /**
     * Constructs an instance of the specified executable (constructor or factory method)
     * using randomly generated values for its parameters.
     *
     * @param executable The constructor or factory method for which an instance needs to be constructed.
     *                   Must not be {@code null}.
     * @return A new instance of the specified class or the return type of the factory method.
     * @throws InvocationTargetException If the underlying constructor or factory method throws an exception.
     * @throws InstantiationException    If the specified class is an abstract class or an interface,
     *                                   or if there is no suitable constructor or factory method.
     * @throws IllegalAccessException    If the constructor or factory method is inaccessible.
     */
    @Generated
    private Object constructInstance(@org.jetbrains.annotations.NotNull Executable executable)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Parameter[] parameters = executable.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = generateRandomValue(parameter.getType().getName(), parameter);
            args[i] = value;
        }

        return executable instanceof Constructor<?>
            ? ((Constructor<?>) executable).newInstance(args)
            : ((Method) executable).invoke(null, args);
    }

    /**
     * Generates a random value for the specified parameter type using the registered random value generators.
     *
     * @param type      The fully qualified name of the parameter type. Must not be {@code null}.
     * @param parameter The parameter for which a random value needs to be generated. Must not be {@code null}.
     * @return A randomly generated value for the specified parameter type.
     * @throws IllegalArgumentException If a null value is generated for a parameter annotated with {@code @NotNull}.
     */
    @Generated
    private @Nullable Object generateRandomValue(
        @org.jetbrains.annotations.NotNull String type,
        @org.jetbrains.annotations.NotNull Parameter parameter
    ) {
        RandomValueGenerator<?> generator = randomValueGenerators.get(type);

        if (generator != null) {
            Object value = generator.generateRandomValue(parameter);
            NotNull notNull = parameter.getAnnotation(NotNull.class);

            if (notNull != null && value == null) {
                throw new IllegalArgumentException("Null value generated for @NotNull parameter");
            }

            return value;
        }

        return null;
    }
}
