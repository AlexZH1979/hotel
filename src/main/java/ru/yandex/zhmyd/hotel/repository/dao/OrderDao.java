package ru.yandex.zhmyd.hotel.repository.dao;

import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;

public interface OrderDao extends GenericDao<OrderEntity, Long> {
    public UserEntity getUserByOrderId(final Long roomOrderId);
    public RoomEntity getRoomByOrderId(final Long roomOrderId);
}
