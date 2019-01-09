package ru.matevosyan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Practice with threads.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 06.08.2017
 */

public class SimpleThread {
    private String className;
    private Thread spaceThread;
    private Thread wordThread;
    private  String text;
    private  long millis;
    private boolean isInterrupted = false;

    /**
     * Constructor.
     * @param className name of starting thread.
     * @param text is string to read.
     * @param millis milliseconds.
     */

    public SimpleThread(String className, String text, long millis) {
        this.className = className;
        this.millis = millis;
        this.text = text;
    }

    /**
     * Default constructor.
     */

    public SimpleThread() {

    }

    /**
     * start thread and if it doing work over 1000 milliseconds, interrupt the thread.
     * @throws InterruptedException if some things goes wrong.
     */

    public void startThreads() throws InterruptedException {
        initAllThreads();
        long start = System.currentTimeMillis();
        boolean isSpaceCounter = CountSpace.class.getName().contains(this.className);

        if (isSpaceCounter) {
            this.spaceThread.start();
        } else {
            this.wordThread.start();
        }

        try {
            if (isSpaceCounter) {
                this.spaceThread.join(millis);
            } else {
                this.wordThread.join(millis);
            }

            if (System.currentTimeMillis() - start > millis) {
                if (this.spaceThread.isAlive() || this.wordThread.isAlive()) {
                    if (isSpaceCounter) {
                        this.spaceThread.interrupt();

                    } else {
                        this.wordThread.interrupt();

                    }

                    if (isSpaceCounter) {
                        this.spaceThread.join();
                    } else {
                        this.wordThread.join();
                    }
                }
            }
        } catch (InterruptedException ie) {
            System.out.println("Stop thread");
        }

        if (this.isInterrupted) {
            System.out.println("Finish");
        }
    }

    /**
     * initialisation threads and out messages.
     */

    private void initAllThreads() {
        this.spaceThread = new Thread(new CountSpace(this.text));
        this.wordThread = new Thread(new CountWord(this.text));
        System.out.printf("Class name %s is running \n", className);
    }

    /**
     * CountSpace created to count spaces in a string.
     */

    public  final class CountSpace implements Runnable {

        /**
         * Constructor.
         * @return sentences.
         */
        public String getSentences() {
            return sentences;
        }

        private String sentences;
        private int countSpace = 0;


        /**
         * Constructor that assign sentences value.
         * @param sentences passing string.
         */

        public CountSpace(final String sentences) {
            this.sentences = sentences;
        }

        /**
         * default constructor.
         */
        public CountSpace() {
        }

        /**
         * Getter for counterSpace.
         * @return countSpace.
         */

        public int getCountSpace() {
            return this.countSpace;
        }

        /**
         * Override run to run CountSpace thread.
         */

        @Override
            public void run() {
                if (this.sentences != null) {
                    char[] charArray = this.sentences.toCharArray();
                    for (char aCharArray : charArray) {
                        if (aCharArray == 32) {
                            this.countSpace++;
                        }
                        if (Thread.currentThread().isInterrupted()) {
                            isInterrupted = true;
                            break;
                        }
                    }

                    System.out.printf("%s spaces\n", this.countSpace);

                }
            }
    }

    /**
     * CountWord created to count words in a string.
     */

    public final class CountWord implements Runnable {

        private String sentences;
        private int countWord = 0;
        private static final String TEXT_PATTERN = "(\\s+)?\\S+(\\s*)?";

        /**
         * Constructor that assign sentences value.
         * @param sentences passing string.
         */

        public CountWord(final String sentences) {
            this.sentences = sentences;
        }

        /**
         * default constructor.
         */

        public CountWord() {
        }

        /**
         * Getter for countWord.
         * @return countWord.
         */

        public int getCountWord() {
            return this.countWord;
        }

        /**
         * Override run to run CountWord thread.

         */

        @Override
        public void run() {
            if (this.sentences != null) {

                Matcher matcher = Pattern.compile(TEXT_PATTERN).matcher(this.sentences);
                while (matcher.find()) {
                    this.countWord++;
                    if (Thread.currentThread().isInterrupted()) {
                        isInterrupted = true;
                        break;
                    }
                }

                System.out.printf("%s words\n", this.countWord);
            }
        }
    }

}
