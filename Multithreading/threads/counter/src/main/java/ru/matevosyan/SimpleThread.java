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

    /**
     * Default constructor.
     */

    public SimpleThread() {
    }

    /**
     * CountSpace created to count spaces in a string.
     */

    public  final class CountSpace implements Runnable {

        private final String sentences;
        private int countSpace = 0;

        /**
         * Getter for counterSpace.
         * @return countSpace.
         */

        public int getCountSpace() {
            return this.countSpace;
        }

        /**
         * Constructor that assign sentences value.
         * @param sentences passing string.
         */

        public CountSpace(final String sentences) {
            this.sentences = sentences;
        }

        /**
         * Override run to run CountSpace thread.
         */

        @Override
            public void run() {
                char[] charArray = this.sentences.toCharArray();
                for (char aCharArray : charArray) {
                    if (aCharArray == 32) {
                        this.countSpace++;
                    }
                }
                for (int i = 0; i < 5000; i++) {
                    System.out.println(this.countSpace);
                }
            }
    }

    /**
     * CountWord created to count words in a string.
     */

    public final class CountWord implements Runnable {

        private final String sentences;
        private int countWord = 0;
        private static final String TEXT_PATTERN = "(\\s+)?\\S+(\\s*)?";

        /**
         * Getter for countWord.
         * @return countWord.
         */

        public int getCountWord() {
            return this.countWord;
        }

        /**
         * Constructor that assign sentences value.
         * @param sentences passing string.
         */

        public CountWord(final String sentences) {
            this.sentences = sentences;
        }

        /**
         * Override run to run CountWord thread.
         */

        @Override
        public void run() {

            Matcher matcher = Pattern.compile(TEXT_PATTERN).matcher(this.sentences);

            while(matcher.find()) {
                this.countWord++;
            }

            for (int i = 0; i < 5000; i++) {
                System.out.println(this.countWord);
            }
        }
    }

}
