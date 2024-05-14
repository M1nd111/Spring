package spring.dataBase.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import spring.dataBase.repository.converter.BirthdayConverter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"companyId", "usersChats"})
@EqualsAndHashCode(of = "username")
@Builder
@Entity
@Table(name = "users")
public class User implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

//    @Embedded
//    private PersonalInfo info;
//    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String firstname;
    private String lastname;


//    @Enumerated(EnumType.STRING)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company companyId;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> usersChats = new ArrayList<>();
}
