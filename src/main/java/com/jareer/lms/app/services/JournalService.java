package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.dtos.JournalDTO;
import com.jareer.lms.app.dtos.JournalUpdateDTO;
import com.jareer.lms.app.repositories.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.JournalMapper.JOURNAL_MAPPER;


@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;

    public Journal createJournal(JournalDTO dto) {

        Journal journal = JOURNAL_MAPPER.toEntity(dto);
        return journalRepository.save(journal);
    }

    public Journal getJournalById(Integer id) {
        return journalRepository.findById(id).orElseThrow(() -> new RuntimeException("Journal not found with id: %d".formatted(id)));
    }

    public Page<Journal> getAll(Integer page, Integer size) {
        return journalRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteJournal(Integer id) {
        journalRepository.deleteById(id);
    }

    public Journal updateJournal(JournalUpdateDTO dto) {
        Journal journal = getJournalById(dto.journalID());
        return journalRepository.save(JOURNAL_MAPPER.partialUpdate(dto, journal));
    }
}
