package spring.dataBase.repository;

import spring.dataBase.repository.entity.User;
import spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

}
