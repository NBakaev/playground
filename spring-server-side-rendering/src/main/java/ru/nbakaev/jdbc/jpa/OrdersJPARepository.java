package ru.nbakaev.jdbc.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nbakaev.jdbc.Orders;

import java.util.List;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/20/2016
 *         All Rights Reserved
 */
@Repository
public interface OrdersJPARepository extends CrudRepository<Orders, Long> {

    List<Orders> findAll();


}
