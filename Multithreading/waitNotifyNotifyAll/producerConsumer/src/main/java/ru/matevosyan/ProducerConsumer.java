package ru.matevosyan;

/**
 * Practice with producer - consumer pattern.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 24.10.2017
 */

public class ProducerConsumer {
    private AnotherBlockingQueue<Integer> homeBank = new AnotherBlockingQueue<>(5);

    /**
     * Default constructor.
     */

    public ProducerConsumer() {
    }

    /**
     * run threads.
     */

    public void startProcess() {
        producerEarnMoney.start();
        consumerSpendMoney.start();

        try {
            producerEarnMoney.join();
            consumerSpendMoney.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Producer thread that earn money to the home bank.
     */

    private Thread producerEarnMoney = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                try {
                    homeBank.addToQueue(i);
                    System.out.println("Producer earn " + i + " money");
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Ups something goes wrong...");
                }
            }
        }
    });

    /**
     * Consumer thread that spend Producers earned money.
     */

    private Thread consumerSpendMoney = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
//                try {
                Integer take = null;
                try {
                    take = homeBank.removeFromQueue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer take " + take + " money");

//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    System.out.println("Ups something goes wrong...");
//                }
            }
        }
    });

}
