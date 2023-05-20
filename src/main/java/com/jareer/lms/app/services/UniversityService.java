package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.UniversityDTO;
import com.jareer.lms.app.dtos.UniversityUpdateDTO;
import com.jareer.lms.app.repositories.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.UniversityMapper.UNIVERSITY_MAPPER;


@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    public University createUniversity(UniversityDTO dto) {
        University university = UNIVERSITY_MAPPER.toEntity(dto);
        return universityRepository.save(university);
    }

    public University getUniversityById(Integer id) {
        return universityRepository.findById(id).orElseThrow(() -> new RuntimeException("University not found"));
    }

    public Page<University> getPage(Integer page, Integer size) {
        return universityRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }

    public University updateUniversity(UniversityUpdateDTO dto) {
        University university = getUniversityById(dto.universityID());
        University updateUniversity = UNIVERSITY_MAPPER.partialUpdate(dto, university);
        return universityRepository.save(updateUniversity);
    }
}
