package ru.matevosyan.entity.toXML;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;

/**
 * Entity Entry in the XML.
 * Created on 21.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "entries")
public class Entries {
    private ArrayList<Entry> entry;

    /**
     * Default constructor.
     */
    public Entries() {
    }

//    /**
//     * Getter for entry.
//     * @return entry.
//     */
//    @XmlElement(name = "entry")
//    public String getEntry() {
//        return entry;
//    }
//
//    /**
//     * Setter for entry.
//     * @param entry in entries.
//     */
//
//
//    public void setEntry(String entry) {
//        this.entry = entry;
//    }

    /**
     * Getter for entry.
     * @return entry.
     */

//    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    public ArrayList<Entry> getEntry() {
        return entry;
    }

    /**
     * Setter for entry.
     * @param entry in entry.
     */


    public void setEntry(ArrayList<Entry> entry) {
        this.entry = entry;
    }

}
