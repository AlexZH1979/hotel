package ru.yandex.zhmyd.hotel.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.zhmyd.hotel.model.Gender;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.model.UserRole;

import java.util.List;
import java.util.logging.Logger;

public class UserServiceTest extends AbstractServiceTest{

    private static final Logger LOG = Logger.getLogger(UserServiceTest.class.getName());


    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){

        User user=new User();
        user.setFirstName("ftest");
        user.setLastName("ltest");
        user.setLogin("ru/yandex");
        user.setEmail("test1@yandex.ru1");
        user.setGender(Gender.MALE);
        user.setRole(UserRole.CUSTOMER);
        user.setPassword("ru/yandex");


        LOG.info("SAVE USER: "+user);
        userService.save(user);

        User user1=new User();
        user1.setFirstName("ftest");
        user1.setLastName("ltest");
        user1.setLogin("ru/yandex");
        user1.setEmail("test1@yandex.ru");
        user1.setRole(UserRole.CUSTOMER);
        user1.setPassword("ru/yandex");


        try {
            LOG.info("TEST DOUBLE SAVE USER");
            userService.save(user1);
            throw new Exception();
        }catch (RuntimeException e){
            LOG.info("FIND DOUBLE USER");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOG.info("TEST delete");
        user=userService.getUserByCredits("ru/yandex", "ru/yandex");
        LOG.info("FIND user="+user);
        userService.delete(user.getId());
        LOG.info("DELETED");

        LOG.info("GET USERS FROM INTERVAL 5-15");
        List<User> users=userService.getInterval(5,10);
        LOG.warning(users.toString());
        LOG.warning("FIND: "+users.size());

    }
}
