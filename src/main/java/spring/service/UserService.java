package spring.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dataBase.repository.UserRepository;
import spring.mapper.UserMapper;

@RequiredArgsConstructor
@ToString
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
}
