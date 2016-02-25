package ru.yandex.zhmyd.hotel.service;


import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;

import java.util.List;

public interface SearchHotelService extends SearchParameter{

    public List<Hotel> searchByAddress(String association, String name, Integer begin, Integer count);

    public List<Hotel> searchByName(String name, Integer begin, Integer count);

    public Long lengthSearchByName(String name);

    public Long lengthSearchByAddress(String association, String name);
}
