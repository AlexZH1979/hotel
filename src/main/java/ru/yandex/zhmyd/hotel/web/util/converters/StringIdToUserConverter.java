package ru.yandex.zhmyd.hotel.web.util.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.zhmyd.hotel.model.User;
import ru.yandex.zhmyd.hotel.service.UserService;

@Component
public class StringIdToUserConverter implements Converter<String, User>{

    private static final Logger LOG=Logger.getLogger(StringIdToUserConverter.class);

    @Autowired
    private UserService userService;

    @Override
    public User convert(String source) {
        User user=null;
        try{
            user= userService.getById(Integer.valueOf(source));
        }catch(Exception e){
            LOG.error("Don't convert id="+source+"; to user",e);
        }
        return user;
    }
}
