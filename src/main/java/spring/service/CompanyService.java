package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dataBase.repository.CompanyRepository;
import spring.dataBase.repository.entity.Company;
import spring.dto.CompanyReadDto;
import spring.listener.AccessType;
import spring.listener.EntityEvent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional // только public методы оборачиваются
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new EntityEvent(entity , AccessType.READ));
            return new CompanyReadDto(entity.getId(), entity.getName());
        });
    }

    public List<CompanyReadDto> findAll(){
        return companyRepository.findAll().stream().map(x -> {
            return new CompanyReadDto(x.getId(), x.getName());
        }).collect(Collectors.toList());
    }
}
