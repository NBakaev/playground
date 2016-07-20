/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#jdbc-JdbcTemplate
 *
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/20/2016
 *         All Rights Reserved
 */
@Repository
@Transactional(readOnly = false)
public class JdbcRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer countCustomers(){
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from customer", Integer.class);
        return rowCount;
    }

    public Integer countOrders(){
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from orders", Integer.class);
        return rowCount;
    }


    public List<Orders> getOrders(){
        return this.jdbcTemplate.query(
                "select o.number, c.id, c.address, date, customer, price, date from orders o LEFT JOIN customer c ON o.customer = c.id",
                new RowMapper<Orders>() {
                    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Orders order = new Orders();
                        order.setNumber(rs.getLong("number"));

                        Customer customer = new Customer();
                        customer.setId(rs.getLong("customer"));
                        customer.setAddress(rs.getString("address"));

                        order.setCustomer(customer);
                        order.setPrice( new BigDecimal(  rs.getString("price") ));
                        return order;
                    }
                });
    }

}
