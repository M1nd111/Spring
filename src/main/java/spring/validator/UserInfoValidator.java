package spring.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import spring.dto.UserReadDto;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserReadDto> {
    @Override
    public boolean isValid(UserReadDto value, ConstraintValidatorContext context) {
        return  hasText(value.getFirstname()) || hasText(value.getLastname()) ;
    }
}
