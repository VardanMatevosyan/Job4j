package ru.matevosyan.entity.toXML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Entity Entry in the XML.
 * Created on 02.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

@XmlRootElement(name = "entry")
@XmlType(name = "field")
public class Entry {
    private int field;

    /**
     * Default constructor.
     */
    public Entry() {
    }

    /**
     * Setter for N.
     * @param value for assign.
     */
    public void setValue(int value) {
        this.field = value;
    }

    /**
     * Getter for values.
     * @return values.
     */
//    @XmlElementWrapper(name = "field")
    @XmlElement(name = "field")
    public int getValues() {
        return this.field;
    }
}
