package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.UniversityDTO;
import com.jareer.lms.app.repositories.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<University> getAll() {
        return universityRepository.findAll();
    }

    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }

    public University updateUniversity(UniversityDTO dto, Integer id) {
        University university = getUniversityById(id);
        return universityRepository.save(UNIVERSITY_MAPPER.partialUpdate(dto, university));
    }
}
