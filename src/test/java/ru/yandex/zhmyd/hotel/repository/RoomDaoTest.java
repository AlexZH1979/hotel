package ru.yandex.zhmyd.hotel.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomCategoryEntity;

import java.util.Date;

@Transactional
public class RoomDaoTest extends AbstractDaoTest{
    private static final Logger LOG=Logger.getLogger(RoomDaoTest.class);

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;

    @Test
    public void getFreeRooms(){
        OrderEntity order=new OrderEntity();
        HotelEntity hotelEntity=hotelDao.getById(269);

        Date date=new Date();
        date.setYear(114);
        date.setMonth(11);
        date.setDate(10);
        order.setStartDate(date);

        date=new Date();
        date.setYear(114);
        date.setMonth(11);
        date.setDate(11);
        order.setEndDate(date);
        order.setPlaces((byte) 1);
        order.setRoomCategory(RoomCategoryEntity.ECONOMY);
        order.setHotel(hotelEntity);

        LOG.info(roomDao.getFreeRooms(order));
    }
}
