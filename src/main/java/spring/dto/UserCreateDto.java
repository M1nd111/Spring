package spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value

public class UserCreateDto {
    Long id;
    @Email
    String username;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    LocalDate birthDate;
    @NotBlank
    @Size(min = 3, max = 30)
    String firstname;
    @NotNull
    String lastname;
    String roles;
    Integer companyId;
    MultipartFile image;
}
