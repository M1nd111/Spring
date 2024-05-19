package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dataBase.repository.entity.Role;
import spring.dataBase.repository.entity.User;
import spring.dto.CompanyReadDto;
import spring.dto.UserReadDto;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto>{

    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getLastname(),
                object.getFirstname(),
                object.getRole(),
               object.getCompanyId().getId()
                );
    }


}
