package test;

import org.junit.jupiter.api.Test;
import com.example.ExecutionContext;
import com.example.*;
import com.example.Commands.*;
import com.example.exception.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTest {
    @Test
    public void testPushCommand() throws InvalidParameterException {
        ExecutionContext context = new ExecutionContext();
        PushCommand command = new PushCommand();

        // Test pushing a number
        command.execute(context, "5");
        assertEquals(1, context.getStack().size());
        assertEquals(5.0, context.getStack().peek());

        // Test pushing a named parameter
        context.getNamedParameters().put("param1", 10.0);
        command.execute(context, "param1");
        assertEquals(2, context.getStack().size());
        assertEquals(10.0, context.getStack().peek());

        // Test pushing an invalid parameter
        assertThrows(InvalidParameterException.class, () -> command.execute(context, "not-a-number"));
    }

    @Test
    public void testPopCommand() throws EmptyStackException, InvalidParameterException {
        // Create a test output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);

        PopCommand command = new PopCommand();
        command.SetPrintStream(printStream);

        // Test popping a value from the stack
        command.execute(context);
        assertEquals(0, context.getStack().size());
        assertEquals("5.0\n", outputStream.toString());

        // Test popping from an empty stack
        assertThrows(EmptyStackException.class, () -> command.execute(context));
    }

    @Test
    public void testAddCommand() throws EmptyStackException, InvalidParameterException {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);
        context.getStack().push(7.0);
        AddCommand command = new AddCommand();

        // Test adding two values
        command.execute(context);
        assertEquals(1, context.getStack().size());
        assertEquals(12.0, context.getStack().peek());

        // Test adding with not enough operands
        context.getStack().clear();
        assertThrows(EmptyStackException.class, () -> command.execute(context));
    }

    @Test
    public void testSubtractCommand() throws NotEnoughOperandsException, InvalidParameterException {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);
        context.getStack().push(7.0);
        SubtractCommand command = new SubtractCommand();

        // Test subtracting two values
        command.execute(context);
        assertEquals(1, context.getStack().size());
        assertEquals(-2.0, context.getStack().peek());

        // Test subtracting with not enough operands
        context.getStack().clear();
        assertThrows(NotEnoughOperandsException.class, () -> command.execute(context));
    }

    @Test
    public void testMultiplyCommand() throws NotEnoughOperandsException, InvalidParameterException {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);
        context.getStack().push(7.0);
        MultiplyCommand command = new MultiplyCommand();

        // Test multiplying two values
        command.execute(context);
        assertEquals(1, context.getStack().size());
        assertEquals(35.0, context.getStack().peek());

        // Test multiplying with not enough operands
        context.getStack().clear();
        assertThrows(NotEnoughOperandsException.class, () -> command.execute(context));
    }

    @Test
    // Test whether the PopCommand removes the top value from the stack when the
    // stack is non-empty.
    public void testPopCommandNonEmptyStack() throws Exception {
        // Create a test output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        ExecutionContext context = new ExecutionContext();
        context.getStack().push(3.0);
        PopCommand popCommand = new PopCommand();
        popCommand.SetPrintStream(printStream);
        popCommand.execute(context);
        assertTrue(context.getStack().isEmpty());
    }

    @Test
    // Test whether the AddCommand throws an exception when there are not enough
    // operands on the stack.
    public void testAddCommandNotEnoughOperands() {
        ExecutionContext context = new ExecutionContext();
        AddCommand addCommand = new AddCommand();
        assertThrows(EmptyStackException.class, () -> addCommand.execute(context));
    }

    @Test
    // Test whether the AddCommand throws an exception when there is only one
    // operand on the stack.
    public void testAddCommandSingleOperand() throws Exception {
        ExecutionContext context = new ExecutionContext();
        PushCommand pushCommand = new PushCommand();
        AddCommand addCommand = new AddCommand();

        pushCommand.execute(context, "2.0");
        assertThrows(EmptyStackException.class, () -> addCommand.execute(context));
    }

    @Test
    // Test whether the AddCommand adds the top two numeric operands on the stack
    // and pushes the result to the stack.
    public void testAddCommandNumericOperands() throws Exception {
        ExecutionContext context = new ExecutionContext();
        PushCommand pushCommand = new PushCommand();
        AddCommand addCommand = new AddCommand();

        pushCommand.execute(context, "2.0");
        pushCommand.execute(context, "3.0");

        addCommand.execute(context);

        assertFalse(context.getStack().isEmpty());
        assertEquals(5.0, context.getStack().peek());
    }

    @Test
    // Test whether the AddCommand adds the top two named parameter operands on the
    // stack and pushes the result to the stack.
    public void testAddCommandNamedParameterOperands() throws Exception {
        ExecutionContext context = new ExecutionContext();
        DefineCommand defineCommand = new DefineCommand();
        PushCommand pushCommand = new PushCommand();
        AddCommand addCommand = new AddCommand();

        defineCommand.execute(context, "x", "2.0");
        defineCommand.execute(context, "y", "3.0");

        pushCommand.execute(context, "x");
        pushCommand.execute(context, "y");

        addCommand.execute(context);

        assertFalse(context.getStack().isEmpty());
        assertEquals(5.0, context.getStack().peek());
    }

    @Test
    // Test whether the SubtractCommand throws an exception when there are not
    // enough operands on the stack.
    public void testSubtractCommandNotEnoughOperands() {
        // Create a new calculator and get its execution context
        Calculator calculator = new Calculator();
        ExecutionContext context = calculator.getContext();

        // Create a new SubtractCommand and add only one operand to the stack
        SubtractCommand command = new SubtractCommand();
        context.getStack().push(5.0);

        // Verify that a NotEnoughOperandsException is thrown when the command is
        // executed
        assertThrows(NotEnoughOperandsException.class, () -> {
            command.execute(context);
        });
    }

    @Test
    // Test whether the SubtractCommand throws an exception when there is only one
    // operand on the stack.
    public void testSubtractCommandSingleOperand() {
        // Create a new calculator and get its execution context
        Calculator calculator = new Calculator();
        ExecutionContext context = calculator.getContext();

        // Create a new SubtractCommand and add only one operand to the stack
        SubtractCommand command = new SubtractCommand();
        context.getStack().push(5.0);

        // Verify that a NotEnoughOperandsException is thrown when the command is
        // executed
        assertThrows(NotEnoughOperandsException.class, () -> {
            command.execute(context);
        });
    }

    @Test
    // Test whether the SubtractCommand subtracts the top two numeric operands on
    // the stack and pushes the result to the stack.
    public void testSubtractCommandNumericOperands() throws Exception {
        // Create a new calculator and get its execution context
        Calculator calculator = new Calculator();
        ExecutionContext context = calculator.getContext();

        // Create a new SubtractCommand and add two numeric operands to the stack
        SubtractCommand command = new SubtractCommand();
        context.getStack().push(5.0);
        context.getStack().push(3.0);

        // Execute the command and verify that the result is as expected
        command.execute(context);
        assertEquals(2.0, context.getStack().peek(), 0.0001);
    }

    @Test
    // Test whether the SubtractCommand subtracts the top two named parameter
    // operands on the stack and pushes the result to the stack.
    void testSubtractCommandNamedParameterOperands() throws Exception {
        // Create an execution context and add some named parameters
        ExecutionContext context = new ExecutionContext();
        context.getNamedParameters().put("a", 5.0);
        context.getNamedParameters().put("b", 3.0);

        // Push the names of the named parameters onto the stack
        PushCommand pushCommand = new PushCommand();
        pushCommand.execute(context, "a");
        pushCommand.execute(context, "b");

        // Create a new SubtractCommand and execute it
        SubtractCommand command = new SubtractCommand();
        command.execute(context);

        // Verify that the result is correct
        assertEquals(2.0, context.getStack().pop());
    }

    @Test
    // Test whether the MultiplyCommand throws an exception when there are not
    // enough operands on the stack.
    public void testMultiplyCommandNotEnoughOperands() {
        // Create a new calculator and get its execution context
        Calculator calculator = new Calculator();
        ExecutionContext context = calculator.getContext();

        // Create a new MultiplyCommand and add only one operand to the stack
        MultiplyCommand command = new MultiplyCommand();
        context.getStack().push(5.0);

        // Verify that a NotEnoughOperandsException is thrown when the command is
        // executed
        assertThrows(NotEnoughOperandsException.class, () -> {
            command.execute(context);
        });
    }

    @Test
    // Test whether the DivideCommand throws an exception when there are not enough
    // operands on the stack.
    public void testDivideCommandNotEnoughOperands() {
        ExecutionContext context = new ExecutionContext();
        DivideCommand command = new DivideCommand();
        assertThrows(NotEnoughOperandsException.class, () -> command.execute(context));
    }

    @Test
    // Test whether the DivideCommand throws an exception when the divisor is zero.
    public void testDivideCommandDivisorIsZero() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(5.0);
        context.getStack().push(0.0);
        DivideCommand command = new DivideCommand();
        assertThrows(MathException.class, () -> command.execute(context));
    }

    @Test
    // Test whether the DivideCommand divides the top two numeric operands on the
    // stack and pushes the result to the stack.
    public void testDivideCommandNumericOperands() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(10.0);
        context.getStack().push(2.0);
        DivideCommand command = new DivideCommand();
        command.execute(context);
        assertEquals(5.0, context.getStack().peek(), 0.0001);
    }

    @Test
    // Test whether the SqrtCommand throws an exception when there are not enough
    // operands on the stack.
    public void testSqrtCommandNotEnoughOperands() {
        ExecutionContext context = new ExecutionContext();
        SqrtCommand command = new SqrtCommand();
        assertThrows(NotEnoughOperandsException.class, () -> command.execute(context));
    }

    @Test
    // Test whether the SqrtCommand throws an exception when the operand is
    // negative.
    public void testSqrtCommandNegativeOperand() {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(-1.0);
        SqrtCommand command = new SqrtCommand();
        assertThrows(MathException.class, () -> command.execute(context));
    }

    @Test
    // Test whether the SqrtCommand computes the square root of the top operand on
    // the stack and pushes the result to the stack.
    public void testSqrtCommand() throws Exception {
        ExecutionContext context = new ExecutionContext();
        context.getStack().push(16.0);
        SqrtCommand command = new SqrtCommand();
        command.execute(context);
        assertEquals(4.0, context.getStack().peek(), 0.0001);
    }
}
