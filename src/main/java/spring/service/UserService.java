package spring.service;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dataBase.repository.CompanyRepository;
import spring.dataBase.repository.UserRepository;
import spring.dataBase.repository.entity.Company;
import spring.dataBase.repository.entity.User;
import spring.dto.CompanyReadDto;
import spring.dto.QPredicates;
import spring.dto.UserFilter;
import spring.dto.UserReadDto;
import spring.mapper.UserCreateMapper;
import spring.mapper.UserReadMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static spring.dataBase.repository.entity.QUser.user;


@RequiredArgsConstructor
@ToString
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final UserCreateMapper userCreateMapper;
    private final UserReadMapper userReadMapper;
    private final EntityManager entityManager;


    @Transactional
    public UserReadDto save(UserReadDto entity) {
        userRepository.save(userCreateMapper.map(entity));
        return entity;
    }
    @Transactional
    public boolean delete(Long id) {
        userRepository.delete(userRepository.findById(id).get());
        userRepository.flush();
        return true;
    }
    @Transactional
    public UserReadDto update(Long id, UserReadDto entity) {
        User user = userCreateMapper.map(entity);
        user.setId(id);
        CompanyReadDto companyReadDto = companyService.findById(user.getCompanyId().getId()).get();
        Company company = Company.builder()
                .id(companyReadDto.id())
                .name(companyReadDto.name())
                .build();

        user.setCompanyId(company);
        entityManager.merge(user);
        entityManager.flush();
        return entity;
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream().map(userReadMapper::map).collect(Collectors.toList());
    }

    public List<UserReadDto> findAll(UserFilter filter) {
        List<User> users = findAllByFilter(filter);
        return users.stream()
                .map(userReadMapper::map)
                .collect(Collectors.toList());
    }

    public List<User> findAllByFilter(UserFilter filter) {

        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildAnd();

        return new JPAQuery<User>(entityManager).select(user).from(user).where(predicate).fetch();
    }

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {

        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildAnd();

        return userRepository.findAll(predicate, pageable).map(userReadMapper::map);
    }

}

/*
public List<User> findAllByFilter(UserFilter filter) {
    var cb = entityManager.getCriteriaBuilder();
    var criteria = cb.createQuery(User.class);

    var user = criteria.from(User.class);
    criteria.select(user);

    List<Predicate> predicates = new ArrayList<>();
    if(filter.firstname() != null && !filter.firstname().isBlank()){
        predicates.add(cb.like(user.get("firstname"), filter.firstname()));
    }
    if(filter.lastname() != null && !filter.lastname().isBlank()){
        predicates.add(cb.like(user.get("lastname"), filter.lastname()));
    }
    if(filter.birthDate() != null){
        predicates.add(cb.lessThan(user.get("birthDate"),  filter.birthDate()));
    }
    criteria.where(predicates.toArray(Predicate[]::new));

    return entityManager.createQuery(criteria).getResultList();
}*/
