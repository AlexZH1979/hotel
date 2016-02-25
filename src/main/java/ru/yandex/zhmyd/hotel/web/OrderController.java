package ru.yandex.zhmyd.hotel.web;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.zhmyd.hotel.model.DisplayedOrder;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.model.RoomCategory;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.security.ApplicationUserDetails;
import ru.yandex.zhmyd.hotel.service.HotelService;
import ru.yandex.zhmyd.hotel.service.OrderService;
import ru.yandex.zhmyd.hotel.service.UserService;
import ru.yandex.zhmyd.hotel.service.exceptions.EntityNonFoundException;
import ru.yandex.zhmyd.hotel.service.exceptions.ServiceException;
import ru.yandex.zhmyd.hotel.web.util.vto.ListViewPart;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String getOrders() {
        return "order.list";
    }
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/{order}", method = RequestMethod.GET)
    public ModelAndView orderInfo(@PathVariable Order order) {
        ModelAndView mav;
        try {
            orderService.basicValidateOrder(order);
            mav = new ModelAndView("order.info");
            mav.addObject("displayedOrder", orderService.convertToDisplayedOrder(order));
        } catch (IllegalArgumentException e) {
            mav = new ModelAndView("hotel");
            mav.addObject("hotel", hotelService.getById(order.getHotelId()));
            mav.addObject("error", "Order not correct");
        } catch (NullPointerException e) {/*DON'T REMOVE */
            mav = new ModelAndView("redirect:/error");
            mav.addObject("error", e.getMessage());
        } catch (Exception e) {
            LOG.error(e);
            mav = new ModelAndView("hotel");
            mav.addObject("hotel", hotelService.getById(order.getHotelId()));
            mav.addObject("error", "Order don't modified, try again");
        }
        return mav;
    }
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/register/param", method = RequestMethod.GET)
    public ModelAndView registerOrder(@ModelAttribute Order order, HttpSession session, Authentication authentication) {

        ApplicationUserDetails appUser = (ApplicationUserDetails) authentication.getPrincipal();
        User user = userService.getUserByPrincipal(appUser);
        ModelAndView mav = new ModelAndView();
        try {
            //set user only in server-side
            order.setCustomerId(user.getId());
            LOG.info("ORDER " + order);
            DisplayedOrder displayedOrder = orderService.convertToDisplayedOrder(order);
            mav.addObject("displayedOrder", displayedOrder);
            mav.setViewName("confirm.order");
            session.setAttribute("order", order);
        } catch (NullPointerException e) {/* DON'T REMOVE*/
            session.removeAttribute("order");
            mav = new ModelAndView("redirect:/error");
            mav.addObject("error", "Error oder");
        } catch (Exception e) {
            LOG.error(e);
            mav = new ModelAndView("hotel");
            mav.addObject("hotel", hotelService.getById(order.getHotelId()));
            mav.addObject("roomCategory", RoomCategory.values());
            mav.addObject("error", "Order don't modified, try again");
        }
        return mav;
    }
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/register/send", method = RequestMethod.GET)
    public ModelAndView sendOrder(HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        orderService.save(order);
        return new ModelAndView("redirect:/orders");
    }

    /*
     *
     *========================
     * -------AJAX METHODS----
     * =======================
     *
     */
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/ajax/delete", method = RequestMethod.POST)
    @ResponseBody
    public List<Long> deleteOrders(@RequestBody List<Long> listId) {
        //list orderIds then delete success or object don't exist
        List<Long> deletedId = new ArrayList<>();
        LOG.info("GET to delete List ids="+listId);

        for (Long id : listId) {
            LOG.info("GET to delete id="+id);
            try {
                orderService.delete(id);
                deletedId.add(id);
            } catch (EntityNonFoundException e) {
                //object absent in present time, remove it's from view table
                deletedId.add(id);
                LOG.warn(e.getMessage());
            }catch (ServiceException e){
                //don't delete because order.confirm=true
                LOG.warn(e.getMessage());
            }
        }
        return deletedId;
    }
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = {"/ajax"}, method = RequestMethod.POST)
    @ResponseBody
    public List<DisplayedOrder> getOrders(@RequestBody final ListViewPart part,Authentication authentication) {
        List<DisplayedOrder> displayedOrders=null;
        try {

        ApplicationUserDetails appUser = (ApplicationUserDetails) authentication.getPrincipal();
        Integer userId = userService.getUserByPrincipal(appUser).getId();
        List<Order> orders = orderService.getIntervalOrdersByUserId(userId,part.getFirst(), part.getCount());
        displayedOrders = orderService.convertToDisplayedOrders(orders);
        }catch (Exception ignore){
            //IGNORE
            LOG.error(ignore);
        }
        return displayedOrders;
    }
}