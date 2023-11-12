package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.task1.Expr.*;

/**
 * Tests for Homework 2, Task 1
 */
class Task1Test {
    @Test
    @DisplayName("Evaluate the complex expression provided in the task description")
    void Expr_TaskTest() {
        // Arrange
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        //Act
        double result = res.evaluate();

        //Assert
        Assertions.assertEquals(37, result);
    }

    @Test
    @DisplayName("Test the evaluation of a constant expression")
    void constant_Test() {
        // Arrange
        Expr constant = new Constant(25.0);

        // Act
        double result = constant.evaluate();

        // Assert
        Assertions.assertEquals(25.0, result);
    }

    @Test
    @DisplayName("Test the evaluation of a negation expression")
    void negate_Test() {
        // Arrange
        Expr innerConstant = new Constant(56.0);
        Expr negate = new Negate(innerConstant);

        // Act
        double result = negate.evaluate();

        // Assert
        Assertions.assertEquals(-56.0, result);
    }

    @Test
    @DisplayName("Test the evaluation of an exponentiation expression")
    void exponent_Test() {
        // Arrange
        Expr base = new Constant(2.0);
        Expr exponent = new Exponent(base, 8.0);

        // Act
        double result = exponent.evaluate();

        // Assert
        Assertions.assertEquals(256.0, result);
    }

    @Test
    @DisplayName("Test the evaluation of an addition expression")
    void addition_Test() {
        // Arrange
        Expr left = new Constant(2.1);
        Expr right = new Constant(7.3);
        Expr addition = new Addition(left, right);

        // Act
        double result = addition.evaluate();

        // Assert
        Assertions.assertEquals(9.4, result);
    }

    @Test
    @DisplayName("Test the evaluation of a multiplication expression")
    void multiplication_Test() {
        // Arrange
        Expr left = new Constant(7.9);
        Expr right = new Constant(6.4);
        Expr multiplication = new Multiplication(left, right);

        // Act
        double result = multiplication.evaluate();

        // Assert
        Assertions.assertEquals(50.56, result);
    }
}
