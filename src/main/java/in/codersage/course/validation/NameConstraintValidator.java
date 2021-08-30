package in.codersage.course.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class NameConstraintValidator implements ConstraintValidator<NamePrefix, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        return value.startsWith("CRS");
    }
}
