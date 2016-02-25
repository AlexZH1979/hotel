package ru.yandex.zhmyd.hotel.service;


import ru.yandex.zhmyd.hotel.model.HotelAddress;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;

import java.util.List;
import java.util.Map;

public interface AddressService extends BasicService<HotelAddress, Integer>, SearchParameter {

    List<String> getCounties(String value);

    List<String> getStates();

    //""->"state"->"county"->"city"->"zip"
    List<String> getNameSubParameters(String param, String value);

    void updateHotelAddressFromMap(Integer hotelId, Map<String, String> map);
}
