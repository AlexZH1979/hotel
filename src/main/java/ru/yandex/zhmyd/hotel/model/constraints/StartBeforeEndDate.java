package ru.yandex.zhmyd.hotel.model.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = StartBeforeEndDateValidator.class)
@Documented
public @interface StartBeforeEndDate {

    public static final String MESSAGE = "fields.notMatches";
    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(TYPE)
    @Retention(RUNTIME)
    @Documented
    @interface List {
        StartBeforeEndDate[] value();
    }

    String begin();

    String end();
}
