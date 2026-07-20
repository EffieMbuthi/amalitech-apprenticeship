package org.example.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsTest {

    @Test
    public void invalidAmountExceptionHasCorrectMessage() {
        InvalidAmountException exception = new InvalidAmountException("The amount must be greater than 0");
        assertEquals("The amount must be greater than 0", exception.getMessage());
    }

    @Test
    public void insufficientFundsExceptionHasCorrectMessage() {
        InsufficientFundsException exception = new InsufficientFundsException("Insufficient funds. Current balance: $400.0");
        assertEquals("Insufficient funds. Current balance: $400.0", exception.getMessage());
    }

    @Test
    public void overdraftExceededExceptionHasCorrectMessage() {
        OverdraftExceededException exception = new OverdraftExceededException("Overdraft limit exceeded. Current balance: $-3000.0");
        assertEquals("Overdraft limit exceeded. Current balance: $-3000.0", exception.getMessage());
    }
}