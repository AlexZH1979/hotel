package ru.yandex.zhmyd.hotel.repository.dao.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import ru.yandex.zhmyd.hotel.repository.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.hotel.repository.dao.OrderDao;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;

@Repository
public class OrderDaoImpl extends AbstractHibernateDao<OrderEntity, Long> implements OrderDao {

    @Override
    public UserEntity getUserByOrderId(Long id) {

        OrderEntity order = (OrderEntity) getSession().get(OrderEntity.class, id);
        UserEntity user=order.getCustomer();
        Hibernate.initialize(user);
        return user;
    }

    @Override
    public RoomEntity getRoomByOrderId(Long id){
        OrderEntity order=(OrderEntity) getSession().get(OrderEntity.class, id);
        RoomEntity room=order.getRoom();
        Hibernate.initialize(room);
        return room;
    }
}
