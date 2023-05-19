package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.SubjectDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    SubjectMapper SUBJECT_MAPPER = Mappers.getMapper(SubjectMapper.class);

    Subject toEntity(SubjectDTO subjectDTO);

    SubjectDTO toDto(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject partialUpdate(SubjectDTO subjectDTO, @MappingTarget Subject subject);
}