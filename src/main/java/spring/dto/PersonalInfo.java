package spring.dto;

import java.time.LocalDate;

public record PersonalInfo(
        LocalDate birthDate,
        String firstname,
        String lastname
) {
}
