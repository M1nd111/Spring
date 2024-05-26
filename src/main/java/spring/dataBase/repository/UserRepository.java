package spring.dataBase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import spring.dataBase.repository.entity.User;
import spring.dto.IPersonalInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , QuerydslPredicateExecutor<User> {

    Page<User> findAllBy(Pageable pageable);

    List<User> findFirst3ByCompanyIdIsNotNull(Sort sort);

    @Query(value = "select u.firstname, u.lastname, u.birth_date from users u where company_id = :companyId",
    nativeQuery = true)
    List<IPersonalInfo> findAllByCompanyId_Id(Integer companyId);
//    <T> List<T> findAllByCompanyId_Id(Integer companyId, Class<T> tClass)

    @Modifying(clearAutomatically = true)
    // Modifying записывает данные сразу в базу, не меняя кэш, этот параметр в true чистит кэш
    @Query(
            "update  User u set u.role = :role where u.id in (:ids)"
    )
    int updateRole(String role, Long... ids);

//    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
//    nativeQuery = true)
//    List<User> findAllByUsername(String username);

     Optional<User> findByUsername(String username);

    @Query(
            "select u from User u where u.firstname like %:firstname% or u.lastname like %:lastname%"
    )
    List<User> findAllByFirstnameContainingOrLastnameContaining(String firstname, String lastname);

}



