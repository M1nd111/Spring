package repository;


import integration.TestApplicationRunner;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import spring.ApplicationRunner;

import spring.dataBase.repository.UserRepository;
import spring.dataBase.repository.entity.Company;
import spring.dataBase.repository.entity.Role;
import spring.dto.PersonalInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@ActiveProfiles("test")
@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional // по умолчанию стоит rollback
//@Commit // ставим чтобы тесты сохранялись
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void checkPageable(){
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);
        page.forEach(user -> System.out.println(user.getId()));
        while(page.hasNext()){
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getId()));
        }
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
    }

    @Test
    void findFirst3ByCompanyIdIsNotNull(){
        var user = userRepository.findFirst3ByCompanyIdIsNotNull(Sort.by("firstname")
                        .and(Sort.by("lastname"))
                        .descending());

        assertTrue(!user.isEmpty());
        assertThat(user).hasSize(3);
    }

    @Test
    void checkProjections(){
        Integer n = 3;
        var users = userRepository.findAllByCompanyId_Id(n);
        assertThat(users).hasSize(1);
    }

    @Test
    void updateRoleTest(){
        var result = userRepository.updateRole(Role.ADMIN.toString(), 1L, 2L);
        assertTrue(result > 0);
    }

    @Test
    void findByQueries(){
        var users = userRepository.findAllByFirstnameContainingOrLastnameContaining("a", "a");
        assertTrue(!users.isEmpty());
    }
}
