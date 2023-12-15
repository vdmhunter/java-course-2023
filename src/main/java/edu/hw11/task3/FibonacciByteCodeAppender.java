package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

/**
 * {@code FibonacciByteCodeAppender} generates bytecode to calculate Fibonacci numbers dynamically.
 * It implements the ByteCodeAppender interface from ByteBuddy.
 */
public class FibonacciByteCodeAppender implements ByteCodeAppender {
    /**
     * Applies the bytecode generation for calculating Fibonacci numbers.
     *
     * @param methodVisitor     the visitor for the method to which bytecode is being applied.
     * @param context           the context in which the implementation is being applied.
     * @param methodDescription the description of the method being implemented.
     * @return the {@link Size} representing the stack size and local variable size impact of the generated code.
     */
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        @NotNull Implementation.Context context,
        @NotNull MethodDescription methodDescription
    ) {
        // CHECKSTYLE:OFF: Disable MagicNumber check
        Label l0 = new Label();
        Label l1 = new Label();
        Label l2 = new Label();

        methodVisitor.visitCode();

        // Load the first two Fibonacci numbers onto the stack
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);

        // Load the counter onto the stack
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 5);

        // Start of loop
        methodVisitor.visitLabel(l0);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            4,
            new Object[] {
                Opcodes.INTEGER,
                Opcodes.LONG,
                Opcodes.LONG,
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        // Load counter and input parameter
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 5);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);

        // If counter > input parameter, jump to end of loop
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

        // Calculate next Fibonacci number
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 7);

        // Shift Fibonacci numbers
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 7);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);

        // Increment counter
        methodVisitor.visitIincInsn(5, 1);

        // Jump to start of loop
        methodVisitor.visitJumpInsn(Opcodes.GOTO, l0);

        // End of loop
        methodVisitor.visitLabel(l1);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            4,
            new Object[] {
                Opcodes.INTEGER,
                Opcodes.LONG,
                Opcodes.LONG,
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        // Return appropriate Fibonacci number
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, l2);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitLabel(l2);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            4,
            new Object[] {
                Opcodes.INTEGER,
                Opcodes.LONG,
                Opcodes.LONG,
                Opcodes.INTEGER
            },
            0,
            new Object[] {}
        );

        methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitMaxs(4, 9);
        methodVisitor.visitEnd();

        return new Size(4, 9);
        // CHECKSTYLE:ON: Enable MagicNumber check
    }
}
