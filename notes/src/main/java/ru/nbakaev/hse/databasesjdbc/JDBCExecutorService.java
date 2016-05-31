/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.hse.databasesjdbc;

import java.sql.*;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 5/31/2016
 *         All Rights Reserved
 */
public class JDBCExecutorService {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * @param databaseName name of database which should be created
     * @throws SQLException
     */
    public void createDataBase(String databaseName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_create_db( ? ) }");
        upperProc.registerOutParameter(1, Types.INTEGER);
        upperProc.setString(2, databaseName);
        upperProc.execute();
        Integer result = upperProc.getInt(1);

        assert result.equals(1);
    }

    /**
     * @param databaseName name of database which should be dropped
     * @throws SQLException
     */
    public void dropDataBase(String databaseName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_drop_db( ? ) }");
        upperProc.registerOutParameter(1, Types.INTEGER);
        upperProc.setString(2, databaseName);
        upperProc.execute();
        Integer result = upperProc.getInt(1);

        assert result.equals(1);
    }

    /**
     * Clear table
     *
     * @param databaseName
     * @param tableName
     * @throws SQLException
     */
    public void trancuteTableInDatabase(String databaseName, String tableName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_trancute_table_db( ?, ? ) }");
        upperProc.registerOutParameter(1, Types.INTEGER);
        upperProc.setString(2, databaseName);
        upperProc.setString(3, tableName);
        upperProc.execute();
        Integer result = upperProc.getInt(1);

        assert result.equals(1);
    }

    public void createCustomer(String databaseName, String customerName, String customerAddress, Integer sale) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_insert_customer( ?, ?, ?, ? ) }");
        upperProc.registerOutParameter(1, Types.INTEGER);
        upperProc.setString(2, databaseName);
        upperProc.setString(3, customerName);
        upperProc.setString(4, customerAddress);
        upperProc.setInt(5, sale);
        upperProc.execute();
        Integer result = upperProc.getInt(1);

        assert result.equals(1);
    }


    /**
     * Create tables and insert data
     *
     * @param databaseName
     * @throws SQLException
     */
    public void createTablesAndData(String databaseName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_create_db_data( ? ) }");
        upperProc.registerOutParameter(1, Types.INTEGER);
        upperProc.setString(2, databaseName);
        upperProc.execute();
        Integer result = upperProc.getInt(1);

        assert result.equals(1);
    }


    public JDBCExecutorService(String username, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println("can't load driverObject " + ex);
            return;
        }

        conn = DriverManager.getConnection("jdbc:postgresql://192.168.127.131:5432/postgres", username, password);
    }

    public JDBCExecutorService(String username, String password, String databaseName) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println("can't load driverObject " + ex);
            return;
        }

        conn = DriverManager.getConnection("jdbc:postgresql://192.168.127.131:5432/" + databaseName, username, password);
    }

    public JDBCExecutorService(String username, String password, String database, String address, String port) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println("can't load driverObject " + ex);
            return;
        }

        conn = DriverManager.getConnection("jdbc:postgresql://" + address + ":" + port + "/" + database, username, password);
    }

    public String printCustomerByName(String databaseName, String customerName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_print_customer( ?, ? ) }");
        upperProc.registerOutParameter(1, Types.VARCHAR);
        upperProc.setString(2, databaseName);
        upperProc.setString(3, customerName);
        upperProc.execute();
        String result = upperProc.getString(1);

        return result;
    }

    public String deleteCustomerByName(String databaseName, String customerName) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_delete_customer( ?, ? ) }");
        upperProc.registerOutParameter(1, Types.VARCHAR);
        upperProc.setString(2, databaseName);
        upperProc.setString(3, customerName);
        upperProc.execute();
        String result = upperProc.getString(1);

        return result;
    }

    public String updateCustomerByName(String databaseName, String customerName, String newCustomerName, String newAddress) throws SQLException {

        CallableStatement upperProc = conn.prepareCall("{ ? = call f_update_customer( ?, ?, ?, ? ) }");
        upperProc.registerOutParameter(1, Types.VARCHAR);
        upperProc.setString(2, databaseName);
        upperProc.setString(3, customerName);
        upperProc.setString(4, newCustomerName);
        upperProc.setString(5, newAddress);
        upperProc.execute();
        String result = upperProc.getString(1);

        return result;
    }

}

