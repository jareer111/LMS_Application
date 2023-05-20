package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.*;
import com.jareer.lms.app.domains.user.Student;
import com.jareer.lms.app.dtos.MarkDTO;
import com.jareer.lms.app.dtos.MarkUpdateDTO;
import com.jareer.lms.app.repositories.JournalRepository;
import com.jareer.lms.app.repositories.MarkRepository;
import com.jareer.lms.app.repositories.StudentRepository;
import com.jareer.lms.app.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.MarkMapper.MARK_MAPPER;


@Service
@RequiredArgsConstructor
public class MarkService {
    private final MarkRepository markRepository;
    private final JournalRepository journalRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public Mark createMark(MarkDTO dto) {
        int studentID = dto.studentID();
        int journalID = dto.journalID();
        int subjectID = dto.subjectID();
        Student student = studentRepository.findById(studentID).orElseThrow(
                () -> new RuntimeException("Student not found with id: %d".formatted(studentID)));
        Journal journal = journalRepository.findById(journalID).orElseThrow(
                () -> new RuntimeException("Journal not found with id: %d".formatted(journalID)));
        Subject subject = subjectRepository.findById(subjectID).orElseThrow(
                () -> new RuntimeException("Subject not found with id: %d".formatted(subjectID)));

        Mark mark = MARK_MAPPER.toEntity(dto);
        mark.setStudent(student);
        mark.setJournal(journal);
        mark.setSubject(subject);
        return markRepository.save(mark);
    }

    public Mark getMarkById(Integer id) {
        return markRepository.findById(id).orElseThrow(() -> new RuntimeException("Mark not found with id: %d".formatted(id)));
    }

    public Page<Mark> getAll(Integer page, Integer size) {
        return markRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteMark(Integer id) {
        markRepository.deleteById(id);
    }

    public Mark updateMark(MarkUpdateDTO dto) {
        Mark mark = getMarkById(dto.markID());
        return markRepository.save(MARK_MAPPER.partialUpdate(dto, mark));
    }
}
