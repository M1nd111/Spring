/*
package unit;

import integration.TestApplicationRunner;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import spring.ApplicationRunner;
import spring.dto.UserDto;
import spring.dto.UserReadDto;
import spring.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApplicationRunner.class)
@Transactional // по умолчанию стоит rollback
//@Commit // ставим чтобы тесты сохранялись\
public class UserServiceIt {
    private final static Long USER_1 = 1L;
    private final static Integer COMPANY_1 = 1;
    @Autowired
    UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(6);
    }

    @Test
    void findById() {
        Optional<UserReadDto> result = userService.findById(USER_1);
        assertTrue(result.isPresent());
        result.ifPresent(userDto -> assertEquals("John", userDto.getUsername()));
    }
    @Test
    void save() {
        UserDto userDto = new UserDto("test",
                LocalDate.now(),
                "test",
                "test",
                "test",
                COMPANY_1);

        Optional<UserDto> result = userService.save(userDto);
        assertTrue(result.isPresent());
        result.ifPresent(userDto1 -> assertEquals("test", userDto1.firstname()));
    }
    @Test
    void update() {
        UserReadDto userReadDto = new UserReadDto("test",
                LocalDate.now(),
                "test",
                "test",
                "test",
                COMPANY_1);
        Optional<UserReadDto> result = userService.update(2L, userReadDto);
        assertTrue(result.isPresent());
        result.ifPresent(userDto1 -> assertEquals("test", userDto1.getUsername()));
    }@Test
    void delete() {
        var res = userService.delete(2L);
        assertEquals(true, res);
    }




}
*/
