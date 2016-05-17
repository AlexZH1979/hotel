package ru.yandex.zhmyd.hotel.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.model.DisplayedOrder;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.dao.OrderDao;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;
import ru.yandex.zhmyd.hotel.repository.dao.UserDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;
import ru.yandex.zhmyd.hotel.repository.entity.OrderEntity;
import ru.yandex.zhmyd.hotel.repository.entity.RoomEntity;
import ru.yandex.zhmyd.hotel.repository.entity.UserEntity;
import ru.yandex.zhmyd.hotel.service.OrderService;
import ru.yandex.zhmyd.hotel.service.exceptions.EntityNonFoundException;
import ru.yandex.zhmyd.hotel.service.exceptions.ServiceException;
import ru.yandex.zhmyd.hotel.service.util.mapper.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderEntity, OrderDao, Long> implements OrderService {

    private static final Logger LOG= Logger.getLogger(OrderServiceImpl.class);

    //24*60*60*1000
    private static final int MILISECONDS_IN_DAY = 86400000;

    private static final long serialVersionUID = 6526282443261564971L;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RoomDao roomDao;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public List<Order> getOrdersByUserId(Integer id) {
        UserEntity userEntity=userDao.getById(id);
        Criterion criterion= Restrictions.eq(Associations.CUSTOMER, userEntity);
        return Util.map(mapper, dao.getByCriteria(criterion), Order.class);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Order> getIntervalOrdersByUserId(Integer id, Integer begin, Integer count) {
        UserEntity userEntity=userDao.getById(id);
        Criterion criterion= Restrictions.eq(Associations.CUSTOMER, userEntity);
        return Util.map(mapper, dao.getByCriteria(criterion, begin, count), Order.class);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Order> getIntervalOrdersByHotelId(Integer hotelId, Integer begin, Integer count) {
        HotelEntity hotelEntity=hotelDao.getById(hotelId);
        Criterion criterion= Restrictions.eq(Associations.HOTEL, hotelEntity);
        return Util.map(mapper, dao.getByCriteria(criterion, begin, count), Order.class);
    }


    @Transactional(readOnly = true)
    @Override
    public DisplayedOrder convertToDisplayedOrder(Order order) {
        DisplayedOrder displayedOrder = new DisplayedOrder(order);
        HotelEntity hotelEntity=hotelDao.getById(order.getHotelId());
        displayedOrder.setHotelName(hotelEntity.getName());
        UserEntity userEntity = userDao.getById(order.getCustomerId());
        displayedOrder.setUserFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        return displayedOrder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DisplayedOrder> convertToDisplayedOrders(List<Order> orders) {
        List<DisplayedOrder> displayedOrders = new ArrayList<>(orders.size());
        for (Order order : orders) {
            displayedOrders.add(convertToDisplayedOrder(order));
        }
        return displayedOrders;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 3)
    @SuppressWarnings("all")
    @Override
    public Order confirmOrder(Long orderId, Integer roomId) {
        OrderEntity order=orderDao.getById(orderId);
        //VALIDATE FROM DATABASE
        if(order==null){
            LOG.warn("OrderEntity witch id=" + orderId + " not found");
            throw new NullPointerException("OrderEntity witch id=" + orderId + " not found");
        }
        RoomEntity roomEntity=roomDao.getById(roomId);
        if(roomEntity==null){
            LOG.warn("RoomEntity witch id=" + roomId + " not found");
            throw new NullPointerException("RoomEntity witch id=" + roomId + " not found");
        }
        //Room is free???
        List<RoomEntity> freeRooms = roomDao.getFreeRooms(order);
        if (!freeRooms.contains(roomEntity)) {
            LOG.warn("Order have don't valid free room");
            throw new IllegalArgumentException("Order have don't valid free room");
        }
        long toStartDay = (order.getStartDate().getTime() - new Date().getTime()) / MILISECONDS_IN_DAY;
        long days = 1 + (order.getEndDate().getTime() - order.getStartDate().getTime()) / MILISECONDS_IN_DAY;
        if (toStartDay < 0 || days < 1) {
            LOG.warn("Order have don't valid start and end dates");
            throw new IllegalArgumentException("Order have don't valid start and end dates");
        }
        //END VALIDATE, FILL OrderEntity AND UPDATE
        order.setRoom(roomEntity);
        order.setPrice(roomEntity.getPrice()*days);
        order.setConfirmed(true);
        orderDao.update(order);
        return mapper.map(order, Order.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Order disconfirmOrder(Long orderId) {
        OrderEntity order=orderDao.getById(orderId);
        if(order==null){
            LOG.warn("OrderEntity witch id="+orderId+" not found");
            throw new EntityNonFoundException("OrderEntity witch id="+orderId+" not found");
        }
        Date currentDate=new Date();
        Date endDate=order.getEndDate();
        long days = (endDate.getTime() - currentDate.getTime()) / MILISECONDS_IN_DAY;
        if (days < 0 &&(order.getConfirmed()!=null&&order.getConfirmed())&&endDate.getDay()!=currentDate.getDay()) {
            LOG.warn("Dont disconfirm finished order");
            throw new ServiceException("Dont disconfirm finished order");
        }

        try {
            order.setRoom(null);
            order.setPrice(null);
            order.setConfirmed(false);
            orderDao.update(order);
        }catch (Exception e){
            System.err.println(e);
        }

        return mapper.map(order, Order.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public void delete(Long id){
        LOG.info("GET to delete id=" + id);
        //don't delete confirmed order
        //if not found -> nothing
        // (if found) AND (administrator don't work witch his OR it's non confirm)->delete; else throw
        Order order=getById(id);
        if (order.getConfirmed() == null || !order.getConfirmed()) {
            LOG.info("ORDER with id=" + id + " delete");
            //throw new ServiceException();
            super.delete(id);
        } else {
            LOG.info("ORDER with id=" + id + " don\'t delete, cause - order confirm=true");
            throw new ServiceException("ORDER with id=" + id + " don\'t delete, cause - order not'finded, or " +
                    "order confirm=true");
        }
    }

    @Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
    @Override
    public void basicValidateOrder(Order order) {
        if(hotelDao.getById(order.getHotelId())==null||
                userDao.getById(order.getCustomerId()) == null) {
            LOG.warn("Non present in DB user or hotel");
            throw new NullPointerException();
        } else if(order.getEndDate().compareTo(order.getStartDate()) < 0){
            LOG.warn("ORDER "+order+" NOT VALID");
            throw new IllegalArgumentException("Non valid order ");
        }
    }
}
