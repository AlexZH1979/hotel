package ru.yandex.zhmyd.hotel.web;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.yandex.zhmyd.hotel.model.DisplayedOrder;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.model.Room;
import ru.yandex.zhmyd.hotel.service.OrderService;
import ru.yandex.zhmyd.hotel.service.RoomService;
import ru.yandex.zhmyd.hotel.web.util.vto.ListViewPart;

import java.util.List;

import static ru.yandex.zhmyd.hotel.web.util.PathSelector.HOTEL;
import static ru.yandex.zhmyd.hotel.web.util.PathSelector.USER;

@Controller
@RequestMapping("/orders/ajax")
public class AdministratorOrderAjaxController {
    private static final Logger LOG = Logger.getLogger(AdministratorOrderAjaxController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;
    /*
    * get orders in interval begin->begin+count
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = {"/all"}, method = RequestMethod.POST)
    @ResponseBody
    public List<DisplayedOrder> getAllOrders(@RequestBody final ListViewPart part) {
        List<DisplayedOrder> displayedOrders = null;
        try {
            List<Order> orders = orderService.getInterval(part.getFirst(), part.getCount());
            displayedOrders = orderService.convertToDisplayedOrders(orders);
        } catch (Exception ignore) {
            //IGNORE
            LOG.error(ignore.getMessage(), ignore.getCause());
        }
        return displayedOrders;
    }

    /*
    * get user orders in interval begin->begin+count
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = {"/{selector}/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public List<DisplayedOrder> getOrdersByUserId(@RequestBody final ListViewPart part,
                                                  @PathVariable String selector,
                                                  @PathVariable Integer id) {
            List<Order> orders = null;
                switch (selector) {
                    case USER:
                        orders = orderService.getIntervalOrdersByUserId(id, part.getFirst(), part.getCount());
                        break;
                    case HOTEL:
                        orders = orderService.getIntervalOrdersByHotelId(id, part.getFirst(), part.getCount());
                        break;
                    default:
                        break;
                }
        return orderService.convertToDisplayedOrders(orders);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @ResponseBody
    @RequestMapping(value = "/admin/rooms/{id}", method = RequestMethod.POST)
    public List<Room> findFreeRoom(@PathVariable Long id){
        return roomService.getFreeRoom(id);
    }
}
