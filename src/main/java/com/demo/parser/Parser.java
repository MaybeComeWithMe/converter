package com.demo.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public interface Parser {
    void parse() throws ParserConfigurationException, IOException, SAXException;
}
