package javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    //цена покупки ниже баланса
    @Test
    public void testPayAmountBelowSumBalance() {
        CreditAccount account = new CreditAccount(
                4_000,
                2_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    //цена покупки ниже суммы баланса+кредитный лимит
    @Test
    public void testPayAmountBelowSumBalanceAndCreditLimit() {
        CreditAccount account = new CreditAccount(
                4_000,
                5_000,
                5
        );

        account.pay(6_000);

        Assertions.assertEquals(-2_000, account.getBalance());
    }

    //цена покупки выше суммы баланс+кредитный лимит
    @Test
    public void testPayAmountUpperSumBalanceAndCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                5
        );

        account.pay(7_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    //тест конструктор
    @Test
    public void testConstructor() {
        CreditAccount account = new CreditAccount(100, 500, 5);
        Assertions.assertEquals(100, account.getBalance());
        Assertions.assertEquals(500, account.getCreditLimit());
        Assertions.assertEquals(5, account.getRate());
    }

    //тест исключения на отрицательный кредитный лимит
    @Test
    public void testConstructorWithNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, -10, 5);
        });
    }

    //тест исключения на отрицательный нечальный баланс
    @Test
    public void testConstructorNegativeBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-1_000, 1000, 5);
        });
    }

    //тест исключения на отрицательную ставку
    @Test
    public void testConstructorNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, 5000, -1);
        });
    }

    // тест на отрицательное пополнение баланса
    @Test
    public void shoulAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                5
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    //тест на положительное пополнение баланса
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //тест на проценты при положительном балансе
    @Test
    public void testCountingPercentIfPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.balance = 200;

        Assertions.assertEquals(0, account.yearChange());
    }

    //тест на проценты при отрицательном балансе
    @Test
    public void testCountingPercentIfNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.balance = -200;

        Assertions.assertEquals(-30, account.yearChange());
    }
}