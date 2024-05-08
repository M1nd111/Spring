package spring.mapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.dto.UserDto;

@ToString
@Component
public class UserMapper {
    @Autowired
    private  UserDto userDto;
}
