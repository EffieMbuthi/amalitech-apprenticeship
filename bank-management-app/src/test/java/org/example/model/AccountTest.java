package org.example.model;

import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.OverdraftExceededException;
import org.example.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Customer customer;

    // Fresh customer before each test - keeps tests independent of each
    // other, and avoids any test accidentally depending on state left
    // behind by a previous one.
    @BeforeEach
    public void setUp() {
        customer = new RegularCustomer("Test User", 30, "0700000000", "Nairobi");
    }

    @Test
    public void depositUpdatesBalance() {
        // arrange
        SavingsAccount acc = new SavingsAccount(customer, 1000.0);

        // act
        acc.deposit(500.0);

        // assert
        assertEquals(1500.0, acc.getBalance());
    }

    @Test
    public void depositNegativeAmountThrowsException() {
        // arrange
        SavingsAccount acc = new SavingsAccount(customer, 1000.0);

        // act + assert
        assertThrows(InvalidAmountException.class, () -> acc.deposit(-50.0));
    }

    @Test
    public void withdrawBelowMinimumThrowsException() {
        // arrange
        SavingsAccount account = new SavingsAccount(customer, 1000.0);

        // act + assert
        // balance 1000 - 600 = 400, below the 500 minimum -> should throw
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(600.0));
    }

    @Test
    public void withdrawAtExactMinimumIsAllowed() {
        // arrange
        SavingsAccount account = new SavingsAccount(customer, 1000.0);

        // act
        // balance 1000 - 500 = 500, exactly at minimum -> should succeed
        account.withdraw(500.0);

        // assert
        assertEquals(500.0, account.getBalance());
    }

    @Test
    public void overdraftWithinLimitAllowed() {
        // arrange
        CheckingAccount account = new CheckingAccount(customer, 500.0);

        // act
        // balance 500 - 1000 = -500, within the -1000 overdraft floor -> allowed
        account.withdraw(1000.0);

        // assert
        assertEquals(-500.0, account.getBalance());
    }

    @Test
    public void overdraftExceedThrowsException() {
        // arrange
        CheckingAccount account = new CheckingAccount(customer, 7000.0);

        // act + assert
        // balance 7000 - 10000 = -3000, past the -1000 overdraft floor -> should throw
        assertThrows(OverdraftExceededException.class, () -> account.withdraw(10000.0));
    }
}