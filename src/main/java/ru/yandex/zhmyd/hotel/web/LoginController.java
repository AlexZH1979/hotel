package ru.yandex.zhmyd.hotel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.security.ApplicationUserDetails;
import ru.yandex.zhmyd.hotel.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @PreAuthorize("permitAll")
    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
    public ModelAndView login(Authentication authentication, HttpSession session,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        if (logout != null) {
            session.invalidate();
            mav.setViewName("search.hotel");
        } else if(authentication!=null){
            ApplicationUserDetails appUser = (ApplicationUserDetails) authentication.getPrincipal();
            User user = userService.getUserByPrincipal(appUser);
            mav.addObject("currentUser", user);
            mav.setViewName("profile");
        }
        return mav;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = {"", "/"}, method = {RequestMethod.GET})
    public String firstPage() {
        return "redirect:/hotels/search";
    }

}
