package ru.yandex.zhmyd.hotel.service;


import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;

import java.util.List;

public interface SearchHotelService extends SearchParameter{

    List<Hotel> searchByAddress(String association, String name, Integer begin, Integer count);

    List<Hotel> searchByName(String name, Integer begin, Integer count);

    Long lengthSearchByName(String name);

    Long lengthSearchByAddress(String association, String name);
}
