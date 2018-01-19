package ru.matevosyan;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CashTest class for testing Cash class.
 * @author Matevosyan Vardan.
 * @version 1.0.
 * created at 04.11.2017.
 */

public class CashTest {
    private Model model = new Model(1, "Model1");
    private Model modelToChange = new Model(2, "ChangeModel");
    private Model modelToChange2 = new Model(3, "ChangeMode2");
    private Model modelToChange3 = new Model(5, "ChangeMode3");
    private Model modelToDelete = new Model(5, "model to delete");

    private Cash cash = new Cash();

    /**
     * OutPut to the console passing parameters.
     * @param concurrentHashMap is concurrent map.
     * @param string is string tell what is going on in threads.
     */

    public void outPut(ConcurrentHashMap<Integer, Model> concurrentHashMap, String string) {
        concurrentHashMap.forEach((key, value) -> System.out.printf("%skey %d\nversion %d\nname %s\n\n%n",
                string, key, value.getVersion().get(), value.getModelName()));
    }

    /**
     * when create threads, after invoke update method and pass models, the first model is the same.
     * Than maybe situation when one thread can't change the model because, second thread do it first.
     * And we get message that Thread can't change the model.
     */

    @Test
    public void whenTwoThreadsTryToUpdateOneModelArOneTimeThanMaybeMessageThatThreadCanNotUpdateTheModel() {
        cash.add(model);
        outPut(cash.getCashMap(), "Before update \n");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cash.update(model, modelToChange);
                    outPut(cash.getCashMap(), "After update in thread1 ");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (OptimisticException e) {
                    System.out.println(Thread.currentThread().getName() + " can't update\n" + e.getMessage() + "\n");
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cash.update(model, modelToChange3);
                    outPut(cash.getCashMap(), "After update in thread2 ");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (OptimisticException e) {
                    System.out.println(Thread.currentThread().getName() + " can't update\n" + e.getMessage() + "\n");
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * When FIRST thread update model and SECOND thread delete model at the same time.
     * Than if FIRST thread run first, get message that SECOND thread can't delete.
     * and no such model in the map.
     * If the SECOND thread tun first, get message that FIRST thread can't update.
     * and Version of model to update is not the same or Model that you wanna change does't exist.
     * if there is no such model to update.
     */

    @Test
    public void whenOneThreadTryToUpdateModelAndSecondDeleteSameModelThanGetMessage() {

        cash.add(model);
        cash.add(modelToDelete);
        outPut(cash.getCashMap(), "Before update \n");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cash.update(modelToDelete, modelToChange3);
                    outPut(cash.getCashMap(), "After update in ");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (OptimisticException e) {
                    System.out.println(Thread.currentThread().getName() + " can't update\n" + e.getMessage() + "\n");
                } catch (NoSuchElementException ne) {
                    System.out.println(Thread.currentThread().getName() + " no such element\n" + ne.getMessage() + "\n");
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    cash.delete(modelToDelete);
                    outPut(cash.getCashMap(), "After removing in ");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (NoSuchElementException e) {
                    System.out.println(Thread.currentThread().getName() + " can't delete \n" + e.getMessage() + "\n");
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}