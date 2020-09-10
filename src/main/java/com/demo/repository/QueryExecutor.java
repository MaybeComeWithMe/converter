package com.demo.repository;

import com.demo.entity.XMLField;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Pavel Tkachev
 * @version 1.0
 */
public interface QueryExecutor {
    void prepareTable() throws SQLException;

    void insertValues(Long n);

    List<XMLField> getValues() throws SQLException;
}
