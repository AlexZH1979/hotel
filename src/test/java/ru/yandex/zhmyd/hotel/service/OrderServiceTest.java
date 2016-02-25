package ru.yandex.zhmyd.hotel.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.model.User;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public class OrderServiceTest extends AbstractServiceTest {

    private static final Logger LOG = Logger.getLogger(OrderServiceTest.class.getName());

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        Order order=orderService.getAll().get(0);
        LOG.info(order.toString());

        User  user=userService.getAll().get(0);
        List<Order> orders=orderService.getOrdersByUserId(user.getId());
        LOG.info(orders.toString());

        LOG.info(orderService.getAll().toString());


        Order order1=new Order();
        order1.setHotelId(order.getHotelId());
        order1.setRoomId(order.getRoomId());
        order1.setCustomerId(user.getId());
        order1.setStartDate(new Date());
        order1.setEndDate(new Date());
        order1.setPrice(300.0);
        order1.setConfirmed(false);
        orderService.save(order1);
    }

    @Test
    public void testInterval(){
        LOG.info("Get interval begin=3, count=5: "+orderService.getInterval(3,5));

    }
}

