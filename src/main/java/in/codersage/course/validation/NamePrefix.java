package in.codersage.course.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NameConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NamePrefix {
    String message() default "Name must be prefixed with CRS";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
