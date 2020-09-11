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
    private final String INSERT_TABLE_DATA = "INSERT INTO `TEST` (`FIELD`) VALUES (?)";
    private final String SELECT_TABLE_DATA = "SELECT test.field as field FROM `TEST` test;";


    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public QueryExecutorImpl(Connection connection) {
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(INSERT_TABLE_DATA);
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
    public void insertValues(Long n) throws SQLException {
        Long number = 1L;
        while (number <= n) {
            try {
                preparedStatement.setLong(1, number);
                preparedStatement.addBatch();
                if (n > 5000) {
                    if (number % 5000 == 0) {
                        preparedStatement.executeBatch();
                    }
                    number++;
                } else {
                    number++;
                    preparedStatement.executeBatch();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        preparedStatement.close();
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
