package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.dtos.GroupDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    GroupMapper GROUP_MAPPER = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupDTO groupDTO);

    GroupDTO toDto(Group group);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Group partialUpdate(GroupDTO groupDTO, @MappingTarget Group group);
}