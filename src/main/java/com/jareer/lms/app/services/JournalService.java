package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.dtos.JournalDTO;
import com.jareer.lms.app.dtos.JournalUpdateDTO;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.JournalRepository;
import com.jareer.lms.app.repositories.SubjectRepository;
import com.jareer.lms.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.JournalMapper.JOURNAL_MAPPER;


@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public Journal createJournal(JournalDTO dto) {
        Integer groupID = dto.groupID();
        Group group = groupRepository.findById(groupID).orElseThrow(() -> new ItemNotFoundException("Group not found with id: %d".formatted(groupID)));
        Journal journal = JOURNAL_MAPPER.toEntity(dto);
        journal.setGroup(group);
        return journalRepository.save(journal);
    }

    public Journal getJournalById(Integer id) {
        return journalRepository.findJournalById(id).orElseThrow(() -> new ItemNotFoundException("Journal not found with id: %d".formatted(id)));
    }

    public Page<Journal> getAll(Integer page, Integer size) {
        return journalRepository.findAllJournal(PageRequest.of(page, size));
    }

    public void deleteJournal(Integer id) {
        Journal journal = journalRepository.findJournalById(id).orElseThrow(() -> new ItemNotFoundException("Journal not found with id: %d".formatted(id)));
        journal.setDeleted(true);
        journalRepository.save(journal);
    }

    public Journal updateJournal(JournalUpdateDTO dto) {
        Journal journal = getJournalById(dto.journalID());
        return journalRepository.save(JOURNAL_MAPPER.partialUpdate(dto, journal));
    }

    public Journal addSubject(Integer journalId, Integer subjectId) {
        Journal journal = journalRepository.findJournalById(journalId).orElseThrow(() -> new ItemNotFoundException("Journal not found with id: %d".formatted(journalId)));
        Subject subject = subjectRepository.findBySubjectId(subjectId).orElseThrow(() -> new ItemNotFoundException("Subject not found with id: %d".formatted(subjectId)));

        journal.getSubjects().add(subject);
        return journalRepository.save(journal);
    }

    public Journal addStudent(Integer journalId, Integer studentID) {
        Journal journal = journalRepository.findJournalById(journalId).orElseThrow(() -> new ItemNotFoundException("Journal not found with id: %d".formatted(journalId)));
        User student = userRepository.findById(studentID).orElseThrow(() -> new ItemNotFoundException("Student not found with id: %d".formatted(studentID)));
        journal.getStudents().add(student);
        return journalRepository.save(journal);
    }
}
