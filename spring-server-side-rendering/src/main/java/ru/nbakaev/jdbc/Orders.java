/*
 * Copyright 2016 the original author or authors.
 */

package ru.nbakaev.jdbc;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/20/2016
 *         All Rights Reserved
 */
@Entity
@Table(name = "orders")
public class Orders {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private long number;
    private String date;
    private BigDecimal price;

    @JoinColumn(name = "customer",referencedColumnName = "id")
    @OneToOne
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (number != orders.number) return false;
        if (date != null ? !date.equals(orders.date) : orders.date != null) return false;
        if (price != null ? !price.equals(orders.price) : orders.price != null) return false;
        return customer != null ? customer.equals(orders.customer) : orders.customer == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
