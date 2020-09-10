package com.demo.parser.impl;

import com.demo.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static com.demo.Main.SECOND_FILE_NAME;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class ParserImpl implements Parser {
    public void parse() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(SECOND_FILE_NAME));

        NodeList nodeList = document.getElementsByTagName("entry");
        Long sum = 0L;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            sum += Long.valueOf(node.getAttributes().getNamedItem("field").getNodeValue());
        }

        System.out.println(String.format("Sum of elements in 2.xml: %d ", sum));
    }
}
