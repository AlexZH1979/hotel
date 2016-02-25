package ru.yandex.zhmyd.hotel.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.service.HotelService;
import ru.yandex.zhmyd.hotel.service.OrderService;
import ru.yandex.zhmyd.hotel.service.UserService;
import ru.yandex.zhmyd.hotel.service.exceptions.ServiceException;

import static ru.yandex.zhmyd.hotel.web.util.PathSelector.HOTEL;
import static ru.yandex.zhmyd.hotel.web.util.PathSelector.USER;

@Controller
@RequestMapping("/orders/admin")
@PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')")
public class AdministratorOrderController {

    private static final Logger LOG = Logger.getLogger(AdministratorOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView administrateOrders(@RequestParam(required = false) String error) {
        ModelAndView mav = new ModelAndView("orders.administrator.list");
        mav.addObject("path", "/orders/ajax/all/");
        if (error != null) {
            mav.addObject("error", error);
        }
        return mav;
    }

    @RequestMapping(value = "/{order}", method = RequestMethod.GET)
    public String administrateOrder(@PathVariable Order order,
                                    @RequestParam(required = false) String error,
                                    Model model) {
        String view = "order.administrator.info";
        try {
            model.addAttribute("displayedOrder", orderService.convertToDisplayedOrder(order));
            model.addAttribute("error", error);
        }catch(NullPointerException e){
            view = "redirect:/orders/admin/";
            model.addAttribute("errorMessage", "order is null");
        }
        return view;
    }

    @RequestMapping(value = "/confirm/", method = RequestMethod.GET)
    public String orderConfirm(@RequestParam(required = true) Long orderId,
                               @RequestParam(required = true) Integer roomId,
                               Model model) {
        String view = "redirect:/orders/admin/" + orderId;
        try {
            orderService.confirmOrder(orderId, roomId);
        } catch (IllegalArgumentException e) {
            LOG.warn(e);
            model.addAttribute("error",e.getMessage());
        }catch (NullPointerException e){
            LOG.warn(e);
            view="redirect:/error";
            model.addAttribute("error",e.getMessage());
        }
        return view;
    }

    @RequestMapping(value = "/disconfirm/", method = RequestMethod.GET)
    public String orderDisconfirm(@RequestParam(required = true) Long orderId,Model model) {
        String view = "redirect:/orders/admin/" + orderId;
        try {
            orderService.disconfirmOrder(orderId);
        } catch (ServiceException e) {
            LOG.warn(e);
            model.addAttribute("error", e.getMessage());
        }
        return view;
    }

    @RequestMapping(value = "/{selector}/{id}", method = RequestMethod.GET)
    public ModelAndView showAdministrateOrder(@PathVariable String selector, @PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("orders.administrator.list");
        try {
            switch (selector) {
                case USER:
                    mav.addObject("path", "/orders/ajax/user/" + id);
                    mav.addObject("nameItem", "User");
                    mav.addObject("item", userService.getById(id).getFirstName());
                    break;
                case HOTEL:
                    mav.addObject("path", "/orders/ajax/hotel/" + id);
                    mav.addObject("nameItem", "Hotel");
                    mav.addObject("item", hotelService.getById(id).getName());
                    break;
                default:
                    mav.addObject("nameItem", "Show all");
                    mav.addObject("path", "/orders/ajax/all/");
                    mav.addObject("item", "orders");
            }
        }catch(NullPointerException e){
            LOG.warn(e);
            mav.addObject("error", "BAD REQUEST PARAMETER");
        }
        return mav;
    }
}
