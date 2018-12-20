package ru.matevosyan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application.
 */
@SpringBootApplication
public class Application {
    /**
     * Main runner.
     * @param args app arguments.
     */
    public static void main(String ... args) {
        SpringApplication.run(Application.class, args);
    }
}
