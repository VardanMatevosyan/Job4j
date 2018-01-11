package ru.matevosyan;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Model class.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 04.11.2017
 */

public class Model {

//    private volatile int version;
    private AtomicInteger version = new AtomicInteger();
    private final int id;
    private String modelName = "";

    /**
     * Model constructor.
     * @param id is models id.
     * @param modelName is model name.
     */

    public Model(int id, String modelName) {
        this.id = id;
        this.modelName = modelName;
        this.version.set(0);
    }

    /**
     * Getter for version.
     * @return models version.
     */

    public AtomicInteger getVersion() {
        return version;
    }

    /**
     * Getter for models name.
     * @return models name.
     */

    public String getModelName() {
        return modelName;
    }

    /**
     * Getter for models id.
     * @return models id.
     */

    public int getId() {
        return id;
    }

    /**
     * Setter for model name and also increase model version for check if it changed.
     * @param modelName model name.
     */

    public void setModelName(String modelName) {
        this.modelName = modelName;
        this.version.incrementAndGet();
    }
}
