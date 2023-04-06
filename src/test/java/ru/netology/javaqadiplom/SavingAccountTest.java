package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTest {

    @Test
    public void testConstructor() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        Assertions.assertEquals(1000, account.getBalance());
        Assertions.assertEquals(500, account.getMinBalance());
        Assertions.assertEquals(2000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    public void testConstructorWithInvalidBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 500, 1500, 5);
        });
    }

    @Test
    public void testConstructorNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1000, 500, 1500, -5);
        });
    }

    @Test
    public void testPayWithValidAmountBetweenMinAndMaxBalance() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 10);
        Assertions.assertFalse(account.pay(600));
        Assertions.assertEquals(400, account.getBalance());
    }

    @Test
    public void testPay() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.pay(500);
        Assertions.assertTrue(result);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void testPayWithNegativeAmount() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.pay(-500);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testPayBelowMinimumBalance() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.pay(800);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(200, account.getBalance());
    }

    @Test
    public void testPayOverMaximumBalance() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.pay(3000);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void testPayWithZeroAmount() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.pay(0);
        Assertions.assertFalse(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testAdd() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.add(500);
        Assertions.assertEquals(true, result);
        Assertions.assertEquals(1500, account.getBalance());
    }

    @Test
    public void testAddWithNegativeAmount() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.add(-500);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testAddAboveMaximumBalance() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        boolean result = account.add(1500);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        int result = account.yearChange();
        Assertions.assertEquals(50, result);
    }

    @Test
    void testYearChangeZeroRate() {
        SavingAccount account = new SavingAccount(1000, 500, 2000, 5);
        account.setRate(0);
        assertEquals(0, account.yearChange());
    }
}
