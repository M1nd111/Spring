package spring.service;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spring.dataBase.repository.CompanyRepository;
import spring.dataBase.repository.UserRepository;
import spring.dataBase.repository.entity.Company;
import spring.dataBase.repository.entity.User;
import spring.dto.QPredicates;
import spring.dto.UserCreateDto;
import spring.dto.UserFilter;
import spring.dto.UserReadDto;
import spring.mapper.UserCreateMapper;
import spring.mapper.UserReadMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static spring.dataBase.repository.entity.QUser.user;


@RequiredArgsConstructor
@ToString
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final UserCreateMapper userCreateMapper;
    private final UserReadMapper userReadMapper;
    private final EntityManager entityManager;
    private final ImageService imageService;



    public Optional<byte[]> findAvatar(long id){
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @Transactional
    public UserReadDto save(UserCreateDto entity) {
        System.out.println(entity);
        User user = userCreateMapper.map(entity);

        // Объединение сущности Company с текущей сессией
        Company company = entityManager.merge(user.getCompanyId());
        user.setCompanyId(company);

        uploadImage(entity.getImage());

        userRepository.save(user);
        return userReadMapper.map(user);
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()){
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Transactional
    public boolean delete(Long id) {
        userRepository.delete(userRepository.findById(id).get());
        userRepository.flush();
        return true;
    }
    @Transactional
    public UserReadDto update(Integer id, UserCreateDto entity) {
        User user = userCreateMapper.map(entity);
        user.setId(id);

        // Объединение сущности Company с текущей сессией
        Company company = entityManager.merge(user.getCompanyId());
        user.setCompanyId(company);
        uploadImage(entity.getImage());
        entityManager.merge(user);
        entityManager.flush();
        return userReadMapper.map(user);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user ->
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to find user" + username));
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
