package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.dtos.auth.UserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserCreateDTO toDto(User user);

    @Mapping(target = "username", source = "email")
    User toEntity(UserCreateDTO userDto);
}
