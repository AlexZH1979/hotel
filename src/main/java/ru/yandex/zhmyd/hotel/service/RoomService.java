package ru.yandex.zhmyd.hotel.service;

import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.model.Room;

import java.util.List;

public interface RoomService extends BasicService<Room, Integer>{
    List<Room> getFreeRoom(Order order);

    List<Room> getFreeRoom(Long id);
}
