package ru.yandex.zhmyd.hotel.service;

import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.model.Room;

import java.util.List;

public interface HotelService extends BasicService<Hotel, Integer>{

    List<Room> getRoomsByHotel(Hotel hotel);

    List<Room> getRoomsByHotel(Integer id);
}
