package ru.matevosyan.entity.FromXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Entity Entry in the XML .
 * Created on 22.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = "field")

public class Entry {
    private String field;

    /**
     * Constructor Entry.
     */
    public Entry() {
    }

    /**
     * Getter for attr field.
     * @return entry.
     */

    @XmlAttribute(name = "field")
    public String getField() {
        return field;
    }

    /**
     * Setter for field.
     * @param field attr value.
     */

    public void setField(String field) {
        this.field = field;
    }
}
