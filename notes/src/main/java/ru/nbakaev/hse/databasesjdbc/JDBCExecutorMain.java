/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.hse.databasesjdbc;

import java.sql.SQLException;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 5/31/2016
 *         All Rights Reserved
 */
public class JDBCExecutorMain {

    public static void main(String[] args) throws SQLException {

        String username = System.getProperty("username") == null ? "postgres" : System.getProperty("username");
        String password = System.getProperty("password") == null ? "postgres" : System.getProperty("password");

        // main database connection
        // where we have create/DROP DB admin stored functions
        JDBCExecutorService mainDatabaseConnection = new JDBCExecutorService(username, password);

        // our working db
        String databaseName = "hse5";

        // drop old database
        mainDatabaseConnection.dropDataBase(databaseName);

        // create new one
        mainDatabaseConnection.createDataBase(databaseName);

        // inserts tables, objects
        mainDatabaseConnection.createTablesAndData(databaseName);

        // clear all orders
        mainDatabaseConnection.trancuteTableInDatabase(databaseName, "orders");

        // create 10 customers
        for (int i = 0; i < 10; i++) {
            mainDatabaseConnection.createCustomer(databaseName, "Nikita " + i, "Esening", 5);
        }

        // in created yet database we have created stored procedure
        JDBCExecutorService databaseConnection = new JDBCExecutorService(username, password, databaseName);
        // expect 'Nikita 7, Адрес: Esening'
        System.out.println(databaseConnection.printCustomerByName(databaseName, "Nikita 7"));

        databaseConnection.deleteCustomerByName(databaseName, "Nikita 7");
        // expect null after delete
        System.out.println(databaseConnection.printCustomerByName(databaseName, "Nikita 7"));

        // change customer from Nikita 8(created above) to Nikita 100 and update address
        // that print
        databaseConnection.updateCustomerByName(databaseName, "Nikita 8", "Nikita 100", "New address");
        System.out.println(databaseConnection.printCustomerByName(databaseName, "Nikita 100"));

    }

}

