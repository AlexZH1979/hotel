package ru.yandex.zhmyd.hotel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.security.ApplicationUserDetails;
import ru.yandex.zhmyd.hotel.service.UserService;

/*
*
* Display and control user profile
* @RequestMapping value=/profile
* TODO
* User.role=Role.CUSTOMER, can see and edit only ouver profile, all userId redirect to current user profile page
* User.role=Role.MODERATOR, can see all profile, but can edit only  your profile
*
*/

@Controller
@RequestMapping("/profile")
@PreAuthorize("isFullyAuthenticated()")
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String showCurrentUserProfile(Model model, Authentication authentication) {
        ApplicationUserDetails appUser = (ApplicationUserDetails) authentication.getPrincipal();
        User user = userService.getUserByPrincipal(appUser);
        model.addAttribute("currentUser", user);
        return "profile";
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String showUserProfile(@PathVariable("userId") User userProfiled, Model model) {
        model.addAttribute("currentUser", userProfiled);
        return "profile";
    }
}