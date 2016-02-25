package ru.yandex.zhmyd.hotel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.service.UserService;
import ru.yandex.zhmyd.hotel.web.util.vto.ListViewPart;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = {"admin/"}, method = RequestMethod.GET)
    public String getUsers(){
        return "users.list";
    }

    @PreAuthorize("isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = {"/ajax"}, method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUsersAjax(@RequestBody final ListViewPart part){
        return userService.getInterval(Integer.parseInt(part.getFirstResult()), Integer.parseInt(part.getSelectCount()));
    }
}
