package ru.yandex.zhmyd.hotel.service.util.mapper;

import org.apache.log4j.Logger;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Contain the methods for mapping structures of object, using Dozer
 *
 */
public class Util {
    private static final Logger LOG= Logger.getLogger(Util.class);

    public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {
        LOG.debug("GET mapper: "+mapper+"\nGET source: "+source+"\nGET dest type class: "+destType);
        final List<U> dest = new ArrayList<>();
        for (T element : source) {
            dest.add(mapper.map(element, destType));
        }
        LOG.debug("RETURN dest: "+dest);
        return dest;
    }
}
