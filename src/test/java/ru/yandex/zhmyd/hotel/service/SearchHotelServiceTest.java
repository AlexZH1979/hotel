package ru.yandex.zhmyd.hotel.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;


public class SearchHotelServiceTest extends  AbstractServiceTest{

    private static final Logger LOG=Logger.getLogger(OrderServiceTest.class.getName());

    @Autowired
    private SearchHotelService searchHotelService;

    @Autowired
    private HotelService hotelService;

    @Test
    public void getHotels(){
     /*   LOG.info("SEARCH state=MI: "+searchHotelService.searchByAddress("state","MI"));
        LOG.info("SEARCH county=Delaware: "+searchHotelService.searchByAddress("county","Delaware"));*/
        LOG.info("SEARCH state=MI: "+searchHotelService.lengthSearchByAddress("state","MI"));
        LOG.info("SEARCH county=Delaware: "+searchHotelService.lengthSearchByAddress("county","Delaware"));
    }
}
