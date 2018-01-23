package ru.matevosyan.entity.toXML;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Entity Field in the XML.
 * Created on 02.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0.
 */

@XmlRootElement(name = "field")
@XmlType(name = "value")
public class Field {
    private int value;

    /**
     * Default constructor.
     */
    public Field() {
    }

    /**
     * Setter for N.
     * @param value for assign.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for values.
     * @return values.
     */

    @XmlValue
    public int getValues() {
        return this.value;
    }
}
