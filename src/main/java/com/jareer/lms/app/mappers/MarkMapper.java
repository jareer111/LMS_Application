package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Mark;
import com.jareer.lms.app.dtos.MarkDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarkMapper {
    MarkMapper MARK_MAPPER = Mappers.getMapper(MarkMapper.class);

    Mark toEntity(MarkDTO markDTO);

    MarkDTO toDto(Mark mark);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mark partialUpdate(MarkDTO markDTO, @MappingTarget Mark mark);
}