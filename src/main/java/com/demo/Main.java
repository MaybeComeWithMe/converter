package com.demo;

import com.demo.dto.ConnectionConfigDTO;
import com.demo.connector.DBConnector;
import com.demo.entity.XMLField;
import com.demo.filler.ApplicationProperties;
import com.demo.parser.Parser;
import com.demo.parser.impl.ParserImpl;
import com.demo.repository.QueryExecutor;
import com.demo.service.XMLBuilder;
import com.demo.service.XSLTConvertor;
import com.demo.service.impl.XSLTConvertorImpl;
import com.demo.repository.impl.QueryExecutorImpl;
import com.demo.service.impl.XMLBuilderImpl;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.currentTimeMillis;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class Main {
    public static final String PROPERTY_FILE_NAME = "application.yml";
    public static final String TEMPLATE_FILE_NAME = "templates/template.xslt";

    public static final String FIRST_FILE_NAME = "1.xml";
    public static final String SECOND_FILE_NAME = "2.xml";

    public static void main(String[] args) {
        System.out.println("Program started");
        long start = currentTimeMillis();

        /**
         * ApplicationProperties class is responsible
         * for reading and parsing properties to ConnectionConfig from .yum file.
         */
        ApplicationProperties applicationProperties = ApplicationProperties.getApplicationPropertiesInstance();
        ConnectionConfigDTO connectionConfig = applicationProperties.getConnectionConfig();

        /**
         * DBConnector singleton class for creating connection fo DB.
         */
        DBConnector dbConnector = new DBConnector(connectionConfig);

        /**
         * QueryExecutor - it is a service for initializing, inserting ang getting data from table.
         */
        QueryExecutor queryExecutor = new QueryExecutorImpl(dbConnector.getConnection());

        List<XMLField> list = null;
        try {
            long execStart = System.currentTimeMillis();
            queryExecutor.prepareTable();
            queryExecutor.insertValues(dbConnector.getN());
            list = queryExecutor.getValues();
            long execFinish = System.currentTimeMillis();
            System.out.println(String.format("Execution queries to DB took: %d ms;", (execFinish - execStart)));
        } catch (SQLException throwables) {
            System.out.println("Couldn't execute queries !");
            throwables.printStackTrace();
        } finally {
            dbConnector.closeConnection();
        }

        /**
         * XMLBuilder - it is a service for building 1.xml file.
         */
        XMLBuilder xmlBuilder = new XMLBuilderImpl();
        try {
            xmlBuilder.build(list);
        } catch (JAXBException | TransformerException e) {
            System.out.println("XML file building error!");
            e.printStackTrace();
        }

        /**
         * XSLTConvertor - it is a service for transformation existing 1.xml file to 2.xml.
         */
        XSLTConvertor xsltConvertor = new XSLTConvertorImpl();
        try {
            xsltConvertor.convert();
        } catch (TransformerException e) {
            System.out.println("XML file converting error!");
            e.printStackTrace();
        }

        /**
         * Parser - it is a service for parsing 2.xml file and sum calculating.
         */
        Parser parser = new ParserImpl();
        try {
            parser.parse();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("XML file parsing error!");
            e.printStackTrace();
        }

        long end = currentTimeMillis();
        System.out.println(String.format("Program invocation took: %d ms", (end - start)));
    }
}
