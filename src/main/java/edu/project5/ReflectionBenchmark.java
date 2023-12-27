package edu.project5;

import edu.common.Generated;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * The {@code ReflectionBenchmark} class is designed to benchmark various methods of accessing
 * the "name" method in the {@link  Student} class. It uses the JMH
 * framework to measure the performance of direct access, reflection, method handles, and lambda meta factory.
 */
@SuppressWarnings({"unused", "SpellCheckingInspection", "UncommentedMain"})
@State(Scope.Thread)
public class ReflectionBenchmark {
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> function;
    private static final String METHOD_NAME = "name";

    /**
     * Main method to run the JMH benchmark.
     *
     * @param args Command line arguments (not used).
     * @throws RunnerException If an error occurs during the benchmark run.
     */
    @Generated
    public static void main(String[] args) throws RunnerException {

        // CHECKSTYLE:OFF: Disable MagicNumber check
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();
        // CHECKSTYLE:ON: Enable MagicNumber check

        new Runner(options).run();
    }

    /**
     * Sets up the necessary components for benchmarking, including creating a {@link Student} instance,
     * obtaining method references, and setting up a lambda function through {@link LambdaMetafactory}.
     *
     * @throws Throwable If an error occurs during setup.
     */
    @Setup
    public void setup() throws Throwable {
        //noinspection SpellCheckingInspection
        student = new Student("Vladimir", "Dmitriev");
        method = Student.class.getMethod(METHOD_NAME);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        );

        @SuppressWarnings("unchecked")
        Function<Student, String> castedFunction = (Function<Student, String>) callSite.getTarget().invokeExact();

        function = castedFunction;
    }

    /**
     * Benchmarks the performance of direct access to the "name" method in the {@link Student} class.
     *
     * @param bh {@link Blackhole} instance to consume the result and prevent dead code elimination.
     */
    @Benchmark
    public void directAccess(@NotNull Blackhole bh) {
        bh.consume(student.name());
    }

    /**
     * Benchmarks the performance of invoking the "name" method using reflection.
     *
     * @param bh {@link Blackhole} instance to consume the result and prevent dead code elimination.
     * @throws InvocationTargetException If the invoked method throws an exception.
     * @throws IllegalAccessException    If the method is not accessible.
     */
    @Benchmark
    public void reflection(@NotNull Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    /**
     * Benchmarks the performance of invoking the "name" method using {@link MethodHandle}.
     *
     * @param bh {@link Blackhole} instance to consume the result and prevent dead code elimination.
     * @throws Throwable If an error occurs during the method handle invocation.
     */
    @Benchmark
    public void methodHandles(@NotNull Blackhole bh) throws Throwable {
        bh.consume(methodHandle.invoke(student));
    }

    /**
     * Benchmarks the performance of invoking the "name" method using lambda metafactory.
     *
     * @param bh {@link Blackhole} instance to consume the result and prevent dead code elimination.
     */
    @Benchmark
    public void lambdaMetaFactory(@NotNull Blackhole bh) {
        bh.consume(function.apply(student));
    }
}
