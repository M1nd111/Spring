package spring.dataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.dataBase.repository.entity.Company;
import spring.dataBase.repository.pool.CrudRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(
            "select c from Company c join fetch c.locales cl where c.name = :name"
    )
    Optional<Company> findByName(String name);
    List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
