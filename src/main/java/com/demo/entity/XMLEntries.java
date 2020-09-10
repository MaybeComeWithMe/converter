package com.demo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLEntries {

    @XmlElement(name = "entry")
    private List<XMLField> XMLEntry;

    public XMLEntries(List<XMLField> XMLEntry) {
        this.XMLEntry = XMLEntry;
    }

    public XMLEntries() {
    }

    public List<XMLField> getXMLEntry() {
        return XMLEntry;
    }

    public void setXMLEntry(List<XMLField> XMLEntry) {
        this.XMLEntry = XMLEntry;
    }
}

