package spring.dto;

import lombok.Value;
import org.springframework.stereotype.Component;
import spring.dataBase.repository.entity.Company;

import java.time.LocalDate;


public record UserDto(
                String username,
                LocalDate birth_date,
                String firstname,
                String lastname,
                String role,
                Integer company_id
) {
}
