package com.jareer.lms.app.mappers;

import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.dtos.JournalDTO;
import com.jareer.lms.app.dtos.JournalUpdateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JournalMapper {
    JournalMapper JOURNAL_MAPPER = Mappers.getMapper(JournalMapper.class);

    Journal toEntity(JournalDTO journalDTO);

    JournalDTO toDto(Journal journal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Journal partialUpdate(JournalUpdateDTO journalDTO, @MappingTarget Journal journal);
}