package ru.nbakaev;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

/**
 * How use and store and not money in java
 *
 * Created by Nikita Bakaev, ya@nbakaev.ru on 2/6/2016.
 * All Rights Reserved
 */
public class TestMoney {

    public static void main(String[] args) {
        MonetaryAmount();
        System.out.println("===================");
        BigDecimalError();
        System.out.println("===================");
        BigDecimalTrue();
    }

    /**
     * JSR 354
     * Java Money and Currency API
     */
    private static void MonetaryAmount() {
        MonetaryAmount fiveUsd  = Money.of(1.0030, "USD");
        MonetaryAmount fiveUsd2 = Money.of(0.2222, "USD");

        System.out.println(  fiveUsd.subtract(fiveUsd2).getNumber().doubleValue() );
        System.out.println( 1.0030 - 0.2222 );
    }

    private static void BigDecimalError() {

        // not use float constructor
        BigDecimal bigDecimal = new BigDecimal(1.0030);
        BigDecimal bigDecimal2 = new BigDecimal(0.2222);

        System.out.println(  bigDecimal.subtract(bigDecimal2).doubleValue() );
        System.out.println( 1.0030 - 0.2222 );
    }

    private static void BigDecimalTrue() {

        BigDecimal bigDecimal = new BigDecimal("1.0030");
        BigDecimal bigDecimal2 = BigDecimal.valueOf(0.2222);

        System.out.println(  bigDecimal.subtract(bigDecimal2) );
        System.out.println( 1.0030 - 0.2222 );
    }

}
