package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.SubjectDTO;
import com.jareer.lms.app.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.SubjectMapper.SUBJECT_MAPPER;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject createSubject(SubjectDTO dto) {
        Subject subject = SUBJECT_MAPPER.toEntity(dto);
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id: %d".formatted(id)));
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

    public Subject updateSubject(SubjectDTO dto, Integer id) {
        Subject subject = getSubjectById(id);
        return subjectRepository.save(SUBJECT_MAPPER.partialUpdate(dto, subject));
    }
}
