package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created BankTest to test Bank class.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class BankTest {

    /**
     * Created whenTransferAmountFromFirstUserAccountToSecondUserAccountThanCheckThisAmount, to test transfer skills.
     * Testing when one user transfer amount from his first account to another one, who takes amount to his account.
     */

    @Test
    public void whenTransferAmountFromFirstUserAccountToSecondUserAccountThanCheckThisAmount() {

        //assign
        Bank bank = new Bank();

        User firstUser = new User("Tom", "KO23423F");
        Account firstUserAccount = new Account(50.50, "G45FD45-567");

        User secondUser = new User("Luis", "KG56423F");
        Account secondUserAccount = new Account(0.0, "G4H8F45-997");

        double amountToTransfer = 10.0;
        double expectedLastAmount = firstUserAccount.getValue() - amountToTransfer;

        //act
        bank.addUser(firstUser);
        bank.addAccountToUser(firstUser, firstUserAccount);

        bank.addUser(secondUser);
        bank.addAccountToUser(secondUser, secondUserAccount);

        bank.transferMoney(firstUser, firstUserAccount, secondUser, secondUserAccount, amountToTransfer);

        //assert
        assertThat(secondUserAccount.getValue(),is(amountToTransfer));
        assertThat(firstUserAccount.getValue(),is(expectedLastAmount));
    }

    /**
     * Created whenTransferAmountFromFirstUserAccountToSecondUserAccountThanCheckThisAmount, to test transfer skills.
     * Testing when one user transfer amount from his first account to his own account.
     */

    @Test
    public void whenTransferAmountFromFirstUserAccountToFirstUserSecondAccountThanCheckThisAmount() {

        //assign
        Bank bank = new Bank();

        User firstUser = new User("Tom", "KO23423F");
        Account firstUserAccount = new Account(50.50, "G45FD45-567");
        Account firstUserSecondAccount = new Account(0.0, "G4H8F45-997");
        double amountToTransfer = 35.5;
        double expectedLastAmount = firstUserAccount.getValue() - amountToTransfer;

        //act
        bank.addUser(firstUser);
        bank.addAccountToUser(firstUser, firstUserAccount);
        bank.addAccountToUser(firstUser, firstUserSecondAccount);

        bank.transferMoney(firstUser, firstUserAccount, firstUser, firstUserSecondAccount, amountToTransfer);

        //assert
        assertThat(firstUserSecondAccount.getValue(),is(amountToTransfer));
        assertThat(firstUserAccount.getValue(),is(expectedLastAmount));
    }

}