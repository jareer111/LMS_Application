package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.dtos.FacultyDTO;
import com.jareer.lms.app.repositories.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.FacultyMapper.FACULTY_MAPPER;


@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public Faculty createFaculty(FacultyDTO dto) {
        Faculty faculty = FACULTY_MAPPER.toEntity(dto);
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found with id: %d".formatted(id)));
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public void deleteFaculty(Integer id) {
        facultyRepository.deleteById(id);
    }

    public Faculty updateFaculty(FacultyDTO dto, Integer id) {
        Faculty faculty = getFacultyById(id);
        return facultyRepository.save(FACULTY_MAPPER.partialUpdate(dto, faculty));
    }
}
