package com.demo.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLField {
    @XmlElement(name = "field")
    private Long field;

    public Long getField() {
        return field;
    }

    public void setField(Long field) {
        this.field = field;
    }
}
