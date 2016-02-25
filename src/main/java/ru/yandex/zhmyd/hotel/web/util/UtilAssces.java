package ru.yandex.zhmyd.hotel.web.util;
import  org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class UtilAssces {
    public static Class<AccessDeniedException> getException(){
        return AccessDeniedException.class;
    }
}
