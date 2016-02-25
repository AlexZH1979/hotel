package ru.yandex.zhmyd.hotel.service;

import ru.yandex.zhmyd.hotel.model.DisplayedOrder;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.repository.dao.util.SearchParameter;

import java.util.List;

public interface OrderService extends BasicService<Order, Long>, SearchParameter{

    List<Order> getOrdersByUserId(Integer userId);

    List<Order> getIntervalOrdersByUserId(Integer id, Integer begin, Integer count);

    List<Order> getIntervalOrdersByHotelId(Integer hotelId, Integer begin, Integer count);

    DisplayedOrder convertToDisplayedOrder(Order order);

    List<DisplayedOrder> convertToDisplayedOrders(List<Order> orders);

    Order confirmOrder(Long orderId, Integer roomId);

    Order disconfirmOrder(Long orderId);

    void basicValidateOrder(Order order);
}