package ru.yandex.zhmyd.hotel.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.model.Room;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;
import ru.yandex.zhmyd.hotel.service.HotelService;
import ru.yandex.zhmyd.hotel.service.util.mapper.Util;

import java.util.List;

@Service
public class HotelServiceImpl extends AbstractServiceImpl<Hotel, HotelEntity, HotelDao,Integer> implements HotelService{

    @Autowired
    private HotelDao hotelDao;

    @Override
    public List<Room> getRoomsByHotel(Hotel hotel) {
        return getRoomsByHotel(hotel.getId());
    }

    @Override
    public List<Room> getRoomsByHotel(Integer id) {
        List<RoomEntity> roomEntities=hotelDao.getById(id).getRooms();
        return Util.map(mapper, roomEntities, Room.class);
    }
}
