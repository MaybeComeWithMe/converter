package com.demo.service.impl;

import com.demo.entity.XMLEntries;
import com.demo.entity.XMLField;
import com.demo.service.XMLBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.demo.Main.FIRST_FILE_NAME;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class XMLBuilderImpl implements XMLBuilder {

    @Override
    public void build(List<XMLField> xmlFieldList) throws JAXBException, TransformerException {
        if (!xmlFieldList.isEmpty()) {
            XMLEntries xmlEntries = new XMLEntries(xmlFieldList);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLEntries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.marshal(xmlEntries, new File(FIRST_FILE_NAME));

        }
    }
}
