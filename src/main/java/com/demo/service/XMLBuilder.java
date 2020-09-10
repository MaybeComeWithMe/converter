package com.demo.service;

import com.demo.entity.XMLField;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.util.List;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public interface XMLBuilder {
    void build(List<XMLField> xmlFieldList) throws JAXBException, TransformerException;
}
