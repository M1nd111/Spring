//package spring.dataBase.repository.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Convert;
//import jakarta.persistence.Embeddable;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.antlr.v4.runtime.misc.NotNull;
//import spring.dataBase.repository.converter.BirthdayConverter;
//
//import java.sql.Date;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Embeddable
//public class PersonalInfo {
//
//    @Convert(converter = BirthdayConverter.class)
//    @Column(name = "birth_date")
//    @NotNull
//    private Date birthDate;
//    private String firstname;
//    private String lastname;
//}
