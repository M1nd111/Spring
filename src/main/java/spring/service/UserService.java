package spring.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import spring.dataBase.repository.UserRepository;

@RequiredArgsConstructor
@ToString
@Service
public class UserService {
    private final UserRepository userRepository;
}
