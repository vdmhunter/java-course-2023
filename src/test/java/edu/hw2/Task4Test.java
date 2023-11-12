package edu.hw2;

import edu.hw2.task4.StackTraceInspector;
import edu.hw2.task4.StackTraceInspector.CallingInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 2, Task 4
 */
class Task4Test {
    @Test
    @DisplayName("Test StackTraceInspector.callingInfo()")
    void stackTraceInspector_TestCallingInfo() {
        // Arrange
        CallingInfo result = StackTraceInspector.callingInfo();

        //Act
        String methodName = result.methodName();

        //Assert
        Assertions.assertEquals("stackTraceInspector_TestCallingInfo", methodName);
    }
}
