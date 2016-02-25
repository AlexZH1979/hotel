package ru.yandex.zhmyd.hotel.repository.dao;

import ru.yandex.zhmyd.hotel.repository.entity.HotelAddressEntity;

import java.util.List;

public interface HotelAddressDao extends GenericDao<HotelAddressEntity,Integer>{

    //""state"->"county"
    List<String> getStates();

    //""state"->"county"
    List<String> getCounties(String value);

    //""->"state"->"county"->"city"->"zip"
    List<String> getNameSubParameters(String param, String value);

    Object getSubParameters(String param, String value);
}
