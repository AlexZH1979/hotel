package ru.yandex.zhmyd.hotel.model.constraints;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class StartBeforeEndDateValidator implements ConstraintValidator<StartBeforeEndDate, Object> {
        private String begin;
        private String end;
         private String message = StartBeforeEndDate.MESSAGE;
    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p/>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(StartBeforeEndDate constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.begin = constraintAnnotation.begin();
        this.end = constraintAnnotation.end();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p/>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object beginObject = getProperty(value, begin, null);
            final Object endObject = getProperty(value, end, null);

            if (beginObject == null || endObject == null) {
                return false;
            }

            if(!(beginObject instanceof Date)||!(endObject instanceof Date)){
                message = ""+beginObject+" or "+endObject+" not instance of java.util.Date";
                return false;
            }

            Date beginDate= (Date) beginObject;
            Date endDate= (Date) endObject;

            boolean matches=!beginDate.after(endDate);
            if (!matches) {
                String msg = this.message;
                if( this.message == null
                        || "".equals( this.message )
                        || StartBeforeEndDate.MESSAGE.equals( this.message ) ){
                    msg = begin + " is not before from " + end;
                }
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate( msg )
                        .addNode(end).addConstraintViolation();
            }

            return matches;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Object getProperty(Object value, String fieldName,
                               Object defaultValue) {
        Class<?> clazz = value.getClass();
        String methodName = "get" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
        try {
            Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
            return method.invoke(value);
        } catch (Exception e) {
        }
        return defaultValue;
    }
}