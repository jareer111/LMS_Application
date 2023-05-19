package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.dtos.JournalDTO;
import com.jareer.lms.app.repositories.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Journal> getAll() {
        return journalRepository.findAll();
    }

    public void deleteJournal(Integer id) {
        journalRepository.deleteById(id);
    }

    public Journal updateJournal(JournalDTO dto, Integer id) {
        Journal journal = getJournalById(id);
        return journalRepository.save(JOURNAL_MAPPER.partialUpdate(dto, journal));
    }
}
