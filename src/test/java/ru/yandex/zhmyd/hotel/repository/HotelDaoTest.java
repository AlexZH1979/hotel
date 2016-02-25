package ru.yandex.zhmyd.hotel.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.repository.dao.HotelDao;
import ru.yandex.zhmyd.hotel.repository.entity.HotelEntity;

import java.util.List;
import java.util.logging.Logger;

@Transactional
public class HotelDaoTest extends AbstractDaoTest {

    private static final Logger LOG = Logger.getLogger(HotelDaoTest.class.getName());

    @Autowired
    private HotelDao hotelDao;


    @Test
    public void loadHotel() {
        HotelEntity hotel = hotelDao.getById(1);
        LOG.info(hotel.toString());

        LOG.info("COUNT: " + hotelDao.getLength());

    }

    @Test
    public void searchByAddressAssociation(){
        LOG.info("SEARCH state=MI: "+hotelDao.searchAddressAssociation("state","MI", 0, 10).toString());
        LOG.info("SEARCH city=Dublin: "+hotelDao.searchAddressAssociation("city","Dublin", 0, 10).toString());
        LOG.info("SEARCH county=Delaware: "+hotelDao.searchAddressAssociation("county","Delaware", 0, 10).toString());
    }

    @Test
    public void searchLikeAddress(){
        List<HotelEntity> hotels=hotelDao.searchLikeAddress("79", 0, 10);
        for(HotelEntity hotel:hotels){
            LOG.info("SEARCH address LIKE 79: " + hotel.getHotelAddress().getAddress());
            Assert.assertTrue(hotel.getHotelAddress().getAddress().contains("79"));
        }

        hotels=hotelDao.searchLikeAddressAssociation("city","East",0,10);
        for(HotelEntity hotel:hotels){
            LOG.info("SEARCH city LIKE East: " + hotel.getHotelAddress().getCity().getName());
            Assert.assertTrue(hotel.getHotelAddress().getCity().getName().contains("East"));
        }
    }
}
