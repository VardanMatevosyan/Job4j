package ru.matevosyan;

import org.junit.Test;

/**
 * test producer - consumer pattern.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.10.2017
 */

public class ProducerConsumerTest {

    /**
     * when producer earn money, consumer should spend it and out put the process like this.
     * ---------------------
     * Producer earn 1 money
     * Consumer take 1 money
     * Producer earn 2 money
     * Consumer take 2 money
     *        ...
     * ---------------------
     */

    @Test
    public void whenProducerEarnMoneyThanConsumerSpendMoney() {
        ProducerConsumer homeBankLifeCircle = new ProducerConsumer();
        homeBankLifeCircle.startProcess();
    }
}