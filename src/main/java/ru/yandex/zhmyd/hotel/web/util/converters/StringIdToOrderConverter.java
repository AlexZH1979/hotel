package ru.yandex.zhmyd.hotel.web.util.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.zhmyd.hotel.model.Order;
import ru.yandex.zhmyd.hotel.service.OrderService;

@Component
public class StringIdToOrderConverter implements Converter<String, Order>{

    private static final Logger LOG=Logger.getLogger(StringIdToOrderConverter.class);

    @Autowired
    private OrderService orderService;

    @Override
    public Order convert(String source) {
        Order order=null;
        try{
            order= orderService.getById(Long.valueOf(source));
        }catch(Exception e){
            LOG.error("Don't convert id="+source+"; to order",e);
        }
        return order;
    }
}
