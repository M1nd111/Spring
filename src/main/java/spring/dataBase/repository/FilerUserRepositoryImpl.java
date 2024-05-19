package spring.dataBase.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import spring.dataBase.repository.entity.User;
import spring.dto.UserFilter;

import java.util.ArrayList;
import java.util.List;


public class FilerUserRepositoryImpl implements FilterUserRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery();

        var user = criteria.from(User.class);
        criteria.select(user);

        List<Predicate> predicates = new ArrayList<>();
        if(filter.firstname() != null){
            predicates.add(cb.like(user.get("firstname"), filter.firstname()));
        }
        if(filter.lastname() != null){
            predicates.add(cb.like(user.get("lastname"), filter.lastname()));
        }
        if(filter.birthDate() != null){
            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
        }

        return null;
    }
}
