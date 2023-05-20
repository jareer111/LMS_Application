package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.dtos.FacultyDTO;
import com.jareer.lms.app.dtos.FacultyUpdateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FacultyMapper {
    FacultyMapper FACULTY_MAPPER = Mappers.getMapper(FacultyMapper.class);

    Faculty toEntity(FacultyDTO facultyDTO);

    FacultyDTO toDto(Faculty faculty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Faculty partialUpdate(FacultyUpdateDTO facultyDTO, @MappingTarget Faculty faculty);
}