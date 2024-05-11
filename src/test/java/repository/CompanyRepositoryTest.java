package repository;


import annotation.IT;
import integration.TestApplicationRunner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.ApplicationRunner;
import spring.dataBase.repository.CompanyRepository;
import spring.dataBase.repository.entity.Company;

import javax.swing.text.html.parser.Entity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@ActiveProfiles("test")
@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional // по умолчанию стоит rollback
//@Commit // ставим чтобы тесты сохранялись
public class CompanyRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager; // автоматически произойдет инжект
    private static final Integer DELL_ID = 8;
    @Autowired
    private CompanyRepository companyRepository;


    @Test
    @Transactional
    void findByQueries(){
        var company = companyRepository.findByName("AA");
//        var companies = companyRepository.findAllByNameContainingIgnoreCase("a");
//        assertNotNull(company);
//        assertThat(companies).hasSize(7);
    }

    @Test
    @Transactional
    void delete(){
        var company = companyRepository.findById(DELL_ID);
        assertTrue(company.isPresent());
        company.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(DELL_ID).isEmpty());
    }

    @Test
    //Propagation.REQUIRES_NEW открывает новую сессию если даже есть открытая сессия
    //Второй Transactional перезаписывает первый
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    void findById(){
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save(){
        var company = Company.builder()
                .name("AA")
                .locales(Map.of("ru", "aaa", "en", "aaa"))
                .build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }



}
