package ru.matevosyan.entity.FromXml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

/**
 * Entity Entry in the table .
 * Created on 22.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = "entry")
public class Entries {
    private ArrayList<Entry> entry;

    /**
     * Default constructor.
     */
    public Entries() {
    }

    /**
     * Getter for entry.
     * @return entry.
     */

    @XmlElement(name = "entry")
    public ArrayList<Entry> getEntry() {
        return entry;
    }

    /**
     * Setter for entry.
     * @param entry in entries.
     */

    public void setEntry(ArrayList<Entry> entry) {
        this.entry = entry;
    }

}
