package edu.hw2.task1;

/**
 * The {@code Expr} interface represents an expression that can be evaluated to a double value.
 */
public sealed interface Expr {
    /**
     * Evaluates the expression and returns its value.
     *
     * @return the value of the expression
     */
    double evaluate();

    /**
     * The {@code Constant} record represents a constant expression.
     */
    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    /**
     * The {@code Negate} record represents a negation of an expression.
     */
    record Negate(Expr operand) implements Expr {
        @Override
        public double evaluate() {
            return -operand.evaluate();
        }
    }

    /**
     * The {@code Exponent} record represents an exponentiation of a base expression to a power.
     */
    record Exponent(Expr base, double exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), exponent);
        }
    }

    /**
     * The {@code Addition} record represents an addition of two expressions.
     */
    record Addition(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }
    }

    /**
     * The {@code Multiplication} record represents a multiplication of two expressions.
     */
    record Multiplication(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() * right.evaluate();
        }
    }
}
