package org.example.service;

import org.example.model.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionManagerTest {

    @Test
    public void addTransactionIncreasesCount(){
        //Arrange
        TransactionManager transactionManager= new TransactionManager();
        Transaction transaction= new Transaction("ACC001", "WITHDRAWAL", 3000.0, 70000.0);

        //act
        transactionManager.addTransaction(transaction);

        //assert
        assertEquals(1, transactionManager.getTransactionCount());
    }

    @Test
    public void calculateTotalDepositsTest(){
        TransactionManager transactionManager= new TransactionManager();

        Transaction transaction1= new Transaction("ACC001", "WITHDRAWAL", 3000.0, 70000.0);
        Transaction transaction2= new Transaction("ACC001", "DEPOSIT", 10000.0, 80000.0);
        Transaction transaction3= new Transaction("ACC003", "DEPOSIT", 2000.0, 170000.0);
        Transaction transaction4= new Transaction("ACC001", "DEPOSIT", 1000.0, 71000.0);

        transactionManager.addTransaction(transaction1);
        transactionManager.addTransaction(transaction2);
        transactionManager.addTransaction(transaction3);
        transactionManager.addTransaction(transaction4);

        double total= transactionManager.calculateTotalDeposits("ACC001");

        assertEquals(11000.0, total);
    }

    @Test
    public void calculateTotalWithdrawalsTest(){
        TransactionManager transactionManager= new TransactionManager();

        Transaction transaction1= new Transaction("ACC001", "WITHDRAWAL", 3000.0, 70000.0);
        Transaction transaction2= new Transaction("ACC001", "DEPOSIT", 10000.0, 80000.0);
        Transaction transaction3= new Transaction("ACC003", "DEPOSIT", 2000.0, 170000.0);
        Transaction transaction4= new Transaction("ACC001", "DEPOSIT", 1000.0, 71000.0);

        transactionManager.addTransaction(transaction1);
        transactionManager.addTransaction(transaction2);
        transactionManager.addTransaction(transaction3);
        transactionManager.addTransaction(transaction4);

        double total= transactionManager.calculateTotalWithdrawals("ACC001");

        assertEquals(3000.0, total);
    }

}
