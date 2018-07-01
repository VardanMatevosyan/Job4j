package ru.matevosyan.entity;
/**
 * MusicType class.
 * @author Matevosyan Vardan.
 * @version 1.0
 * created 13.06.2018
 */
public class MusicType {
    private int id;
    private String name;
    /**
     * Getter for MusicType id.
     * @return  MusicType id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for MusicType id.
     * @param id MusicType id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Setter for MusicType name.
     * @param name MusicType name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for MusicType name.
     * @return  MusicType name.
     */
    public String getName() {
        return name;
    }
}
