package ru.matevosyan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Admin on 27.04.2017.
 */
public class BankTest {

    @Test
    public void whenTransferAmountThanCheckThisAmount() {

        //assign
        Bank bank = new Bank();

        User firstUser = new User("Tom", "KO23423F");
        Account firstUserAccount = new Account(50.50, "G45FD45-567");

        User secondUser = new User("Luis", "KG56423F");
        Account secondUserAccount = new Account(0.0, "G4H8F45-997");

        //act
        bank.addUser(firstUser);
        bank.addAccountToUser(firstUser, firstUserAccount);

        bank.addUser(secondUser);
        bank.addAccountToUser(secondUser, secondUserAccount);

        bank.transferMoney(firstUser, firstUserAccount, secondUser, secondUserAccount, 10);

        //assert
//        assertThat(bank.getUserAccounts(secondUser).get(0).getValue(),is(10.0));
        assertThat(secondUserAccount.getValue(),is(10.0));
    }

}