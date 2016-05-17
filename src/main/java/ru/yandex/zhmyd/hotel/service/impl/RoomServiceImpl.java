package ru.yandex.zhmyd.hotel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.model.Room;
import ru.yandex.zhmyd.hotel.repository.dao.OrderDao;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;
import ru.yandex.zhmyd.hotel.service.RoomService;
import ru.yandex.zhmyd.hotel.service.util.mapper.Util;

import java.util.List;

@Service
public class RoomServiceImpl extends AbstractServiceImpl<Room, RoomEntity, RoomDao, Integer> implements RoomService{

    private static final long serialVersionUID = -18755606649264967L;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private OrderDao orderDao;

   @Override
    public List<Room> getFreeRoom(Order order){
        return getFreeRoom(order.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Room> getFreeRoom(Long id){
        OrderEntity orderEntity=orderDao.getById(id);
        return Util.map(mapper,roomDao.getFreeRooms(orderEntity),Room.class);
    }
}
