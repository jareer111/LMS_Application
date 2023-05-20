package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.UniversityDTO;
import com.jareer.lms.app.dtos.UniversityUpdateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UniversityMapper {
    UniversityMapper UNIVERSITY_MAPPER = Mappers.getMapper(UniversityMapper.class);

    University toEntity(UniversityDTO universityDTO);

    UniversityDTO toDto(University university);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    University partialUpdate(UniversityUpdateDTO universityDTO, @MappingTarget University university);
}