package com.demo.repository.impl;

import com.demo.entity.XMLField;
import com.demo.repository.QueryExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public class QueryExecutorImpl implements QueryExecutor {

    private final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS `TEST`;";
    private final String CREATE_TABLE = "CREATE TABLE `TEST` (FIELD BIGINT);";
    private final String INSERT_TABLE_DATA = "INSERT INTO `TEST` (`FIELD`) VALUES ('%d')";
    private final String SELECT_TABLE_DATA = "SELECT test.field as field FROM `TEST` test;";


    private Statement statement = null;

    public QueryExecutorImpl(Connection connection) {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void prepareTable() throws SQLException {
        statement.addBatch(DROP_TABLE_IF_EXISTS);
        statement.addBatch(CREATE_TABLE);

        statement.executeBatch();
    }

    @Override
    public void insertValues(Long n) {
        Integer number = 1;
        while (number <= n) {
            try {
                statement.addBatch(String.format(INSERT_TABLE_DATA, number));
                if (n > 500) {
                    if (number % 500 == 0) {
                        statement.executeLargeBatch();
                    }
                    number++;
                } else {
                    number++;
                    statement.executeLargeBatch();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<XMLField> getValues() throws SQLException {
        ResultSet rs = statement.executeQuery(SELECT_TABLE_DATA);
        List<XMLField> xmlFieldList = new ArrayList<>();

        while (rs.next()) {
            XMLField xmlField = new XMLField();
            xmlField.setField(rs.getLong("field"));

            xmlFieldList.add(xmlField);
        }

        statement.close();
        return xmlFieldList;
    }
}
