package edu.hw2.task4;

/**
 * The {@code StackTraceInspector} class provides utility method {@link StackTraceInspector#callingInfo()}
 * to extract information about the caller of a method or function in the Java program.
 * It allows you to retrieve details about the class and method that invoked a particular method.
 */
public final class StackTraceInspector {

    private StackTraceInspector() {
    }

    /**
     * Retrieves information about the caller of the method that invokes this function.
     *
     * @return A {@link  CallingInfo} object containing details about the caller, including the class name
     * and method name from which this function is called.
     */
    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StackTraceElement caller = stackTraceElements[1];

        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }

    /**
     * A record {@code CallingInfo} representing information about the caller of a method.
     * It includes the class name and method name of the caller.
     */
    public record CallingInfo(String className, String methodName) {
    }
}
