package ru.yandex.zhmyd.hotel.repository;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.zhmyd.hotel.repository.dao.RoomDao;

@Transactional
public class BaseTest extends AbstractDaoTest {

    private static final Logger LOG = Logger.getLogger(BaseTest.class);

    @Autowired
    private RoomDao roomDao;

    @Test
    public void basicTest() {
        LOG.info("getByCriteria(null, begin=0, count=10): "+roomDao.getByCriteria(null, 0,10));
    }
}
