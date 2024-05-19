package spring.mapper;

import spring.dataBase.repository.entity.Company;
import spring.dto.CompanyReadDto;
import spring.dto.UserReadDto;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }
}
