package ru.yandex.zhmyd.hotel.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yandex.zhmyd.hotel.model.Hotel;
import ru.yandex.zhmyd.hotel.service.AddressService;
import ru.yandex.zhmyd.hotel.service.HotelService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.yandex.zhmyd.hotel.model.parameters.SearchParameter.ADDRESS;
import static ru.yandex.zhmyd.hotel.model.parameters.SearchParameter.Associations.*;

@Controller
@RequestMapping("/hotels/admin")
public class AdministratorHotelController {

    private static final Logger LOG=Logger.getLogger(AdministratorHotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AddressService addressService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public String hotelList(){ return "hotels.list";}

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editHotel(@PathVariable("id") Hotel hotel, Model model){
        String view="hotel.edit";
        try {
            model.addAttribute(hotel);
        }catch(IllegalArgumentException e){
            view="redirect:/error";
            model.addAttribute("errorMessage",e.getMessage());
        }
        return  view;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editHotelData(@Valid @ModelAttribute Hotel hotel,BindingResult result){
        System.out.println("hotel = " + hotel);
        System.out.println("result = " + result);
        if(result.hasErrors()){
            return "hotel.edit";
        }
        try{
            hotelService.update(hotel);
        }catch (Exception e){
             return "redirect:/error?errorMessage="+e.getMessage();
        }
        return "redirect:/hotels/"+hotel.getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    @SuppressWarnings("all")
    @RequestMapping(value = "/edit/{id}/address/{parameter}/", method = RequestMethod.GET)
     public String editHotelAddress(@PathVariable("id") Integer id,
                                    @PathVariable String parameter,
                                    @ModelAttribute("value") String value,
                                    HttpSession session,
                                    Model model){
        List<String> subParameters;
        String view="hotel.address.edit";

        String beginPath="/hotels/admin/edit/"+id+"/address/";
        model.addAttribute("beginPath",beginPath);

        //Address MAP fill
        Object addressObj=session.getAttribute("addressMap");
        if(addressObj==null||!(addressObj instanceof Map)){
            addressObj=new HashMap<String, String>();
            session.setAttribute("addressMap",addressObj);
        }
        Map<String, String> address= (Map<String, String>) addressObj;
        LOG.info("ADDRESS MAP IN SESSION: "+address);
        //END
        switch (parameter){
            case STATE:
                subParameters=addressService.getStates();
                LOG.info("STATE: " + subParameters);
                //[STATE] for path and next select
                model.addAttribute("parameterForSelect", STATE);
                model.addAttribute("endPath", COUNTY);
                model.addAttribute("valuesList", subParameters);
                break;
            case COUNTY:
                address.put(STATE, value);
                subParameters=addressService.getNameSubParameters(STATE,value);
                LOG.info("COUNTY: "+subParameters);
                model.addAttribute("parameterForSelect", COUNTY);
                model.addAttribute("endPath", CITY);
                model.addAttribute("valuesList", subParameters);
                break;
            case CITY:
                address.put(COUNTY, value);
                subParameters=addressService.getNameSubParameters(COUNTY,value);
                LOG.info("CITY: "+subParameters);
                model.addAttribute("parameterForSelect",  CITY);
                model.addAttribute("endPath", ZIP);
                model.addAttribute("valuesList", subParameters);
                break;
            case ZIP:
                address.put(CITY, value);
                subParameters=addressService.getNameSubParameters(CITY,value);
                LOG.info("ZIP: "+subParameters);
                model.addAttribute("parameterForSelect",ZIP);
                model.addAttribute("endPath", ADDRESS);
                model.addAttribute("valuesList", subParameters);
                break;
            case ADDRESS:
                address.put(ZIP, value);
                model.addAttribute("parameterForSelect",ADDRESS);
                model.addAttribute("endPath", "send");
                return "hotel.address.edit.a";
            case "send":
                address.put(ADDRESS, value);
                model.addAttribute("endPath", "send");
                LOG.info("ADDRESS MAP IN SESSION BEFORE SAVE: "+address);
                //SAVE
                addressService.updateHotelAddressFromMap(id, address);
                view="redirect:/hotels/" + id;
            default:
        }
        return view;
    }
}
