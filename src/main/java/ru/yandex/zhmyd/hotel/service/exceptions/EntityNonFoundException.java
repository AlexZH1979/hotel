package ru.yandex.zhmyd.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Order")
public class EntityNonFoundException extends ServiceException {

    public EntityNonFoundException() {
        super("Entity non found");
    }

    public EntityNonFoundException(String msg) {
        super(msg);
    }

    public String getMsg() {
        return super.getMessage();
    }
}
