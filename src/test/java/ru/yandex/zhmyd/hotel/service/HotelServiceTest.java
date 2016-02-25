package ru.yandex.zhmyd.hotel.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class HotelServiceTest extends AbstractServiceTest{

    private static final Logger LOG=Logger.getLogger(HotelServiceTest.class.getName());

    @Autowired
    private HotelService hotelService;

    @Ignore
    @Test
    public void fullLoadHotel(){
        LOG.info("LOAD HOTEL ID=1: "+hotelService.getById(1));
    }

}
