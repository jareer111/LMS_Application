package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Student;
import com.jareer.lms.app.dtos.StudentDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDTO studentDTO);

    StudentDTO toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDTO studentDTO, @MappingTarget Student student);
}