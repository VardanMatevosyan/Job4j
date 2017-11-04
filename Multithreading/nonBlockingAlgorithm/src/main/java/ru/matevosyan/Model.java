package ru.matevosyan;

/**
 * Model class.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 04.11.2017
 */

public class Model {

    protected int version;
    private int id = 0;
    private String modelName = "";

    /**
     * Model constructor.
     * @param id is models id.
     * @param modelName is model name.
     */

    public Model(int id, String modelName) {
        this.id = id;
        this.modelName = modelName;
        this.version = 0;
    }

    /**
     * Getter for version.
     * @return models version.
     */

    public int getVersion() {
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
}
