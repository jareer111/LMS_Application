package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.SubjectDTO;
import com.jareer.lms.app.dtos.SubjectUpdateDTO;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        return subjectRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Subject not found with id: %d".formatted(id)));
    }

    public Page<Subject> getAll(Integer page, Integer size) {
        return subjectRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteSubject(Integer id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Subject not found with id: %d".formatted(id)));
        subject.setDeleted(true);
        subjectRepository.save(subject);
    }

    public Subject updateSubject(SubjectUpdateDTO dto) {
        Subject subject = getSubjectById(dto.subjectID());
        return subjectRepository.save(SUBJECT_MAPPER.partialUpdate(dto, subject));
    }
}
