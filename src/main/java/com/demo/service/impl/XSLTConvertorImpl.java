package com.demo.service.impl;

import com.demo.service.XSLTConvertor;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import static com.demo.Main.*;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class XSLTConvertorImpl implements XSLTConvertor {

    @Override
    public void convert() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        File fileForParse = new File(this.getClass().getClassLoader().getResource(TEMPLATE_FILE_NAME).getFile());
        Source xslt = new StreamSource(fileForParse);
        Transformer transformer = factory.newTransformer(xslt);
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        Source text = new StreamSource(new File(FIRST_FILE_NAME));
        transformer.transform(text, new StreamResult(new File(SECOND_FILE_NAME)));
    }
}
