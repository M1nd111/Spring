package spring.mapper;

import spring.dto.UserReadDto;

public interface Mapper<F, T> {
    T map(F object);
}
