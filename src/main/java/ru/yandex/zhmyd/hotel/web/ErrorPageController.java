package ru.yandex.zhmyd.hotel.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController {

    @RequestMapping(value = {"/error","/error/"}, method = RequestMethod.GET)
    public String errorPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String returnPage,
                            Model model){
        if(error!=null&&!error.isEmpty()) {
            model.addAttribute(error);
        }
        if(returnPage!=null&&!returnPage.isEmpty()) {
            model.addAttribute(returnPage);
        }
        return "error";
    }

    //and don't worry
   @RequestMapping(value = {"/errors/400.html","/errors/404.html","/errors/405.html","/errors/406.html","/errors/408.html",
                "/errors/415.html","/errors/500.html","/errors/503.html"})
        public ModelAndView handleHttpErrors () {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
