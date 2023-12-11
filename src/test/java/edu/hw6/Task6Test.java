package edu.hw6;

import edu.hw6.task6.PortScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for Homework 6, Task 6
 */
class Task6Test {
    @Test
    @DisplayName("Test that scanPorts does not throw any exceptions")
    void portScanner_TestScanPorts() {
        Assertions.assertDoesNotThrow(PortScanner::scanPorts);
    }
}
