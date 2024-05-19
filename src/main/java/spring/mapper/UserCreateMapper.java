package spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.dataBase.repository.CompanyRepository;
import spring.dataBase.repository.entity.Company;
import spring.dataBase.repository.entity.Role;
import spring.dataBase.repository.entity.User;
import spring.dto.CompanyReadDto;
import spring.dto.UserDto;
import spring.dto.UserReadDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserReadDto, User>{
    private final CompanyRepository companyRepository;
    @Override
    public User map(UserReadDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    public User map(UserReadDto object, User user){
        copy(object, user);
        return user;
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }

    private void copy(UserReadDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthDate(object.getBirthDate());
        user.setRole(Role.QA.toString());
        user.setCompanyId( Company.builder()
                        .id(object.getCompanyId())
                .build() );
    }
}
