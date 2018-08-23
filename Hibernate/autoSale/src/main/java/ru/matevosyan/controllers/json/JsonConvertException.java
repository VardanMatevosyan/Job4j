package ru.matevosyan.controllers.json;

/**
 * Json convert exception.
 */
public class JsonConvertException extends RuntimeException {
    /**
     * JsonConvertException invoke parent method.
     * @param message exception message.
     */
    public JsonConvertException(String message) {
        super(message);
    }
}
