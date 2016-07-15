package ru.nbakaev.interfaceimplement.example;

import ru.nbakaev.interfaceimplement.MicroserviceRequest;

import java.util.List;

/**
 * @author Nikita Bakaev, ya@nbakaev.ru
 *         Date: 7/15/2016
 *         All Rights Reserved
 */
@MicroserviceRequest
public interface UserMicroserviceRequest {

    List<String> getAlluserIds();

}
