package ru.yandex.zhmyd.hotel.controler;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.web.HotelController;
import ru.yandex.zhmyd.hotel.web.util.vto.SearchParam;

import java.util.logging.Logger;

public class HotelControllerTest extends AbstractControllerTest{

    private static final Logger LOG =Logger.getLogger(HotelControllerTest.class.getName());

    @Autowired
    private HotelController hotelController;

    @Test
    public void searchTest(){
        //LOG.info(hotelController.searchHotelsByParameter("MI").toString());
        /*for(String st:hotelController.getStates()) {
            LOG.info(hotelController.lengthSearchHotelsByParameter(st, "state").toString());
        }*/
        LOG.info(""+hotelController.lengthSearchHotelsByParameter(new SearchParam("state", "AK")));
    }
}
