package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.FacultyDTO;
import com.jareer.lms.app.dtos.FacultyUpdateDTO;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.FacultyRepository;
import com.jareer.lms.app.repositories.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.FacultyMapper.FACULTY_MAPPER;


@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    public Faculty createFaculty(FacultyDTO dto) {
        int universityID = dto.universityID();
        University university = universityRepository.findById(universityID).orElseThrow(() -> new ItemNotFoundException("University not found with id: %d".formatted(universityID)));
        Faculty faculty = FACULTY_MAPPER.toEntity(dto);
        faculty.setUniversity(university);
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findFacultyById(id).orElseThrow(() -> new ItemNotFoundException("Faculty not found with id: %d".formatted(id)));
    }

    public Page<Faculty> getPage(Integer page, Integer size) {
        return facultyRepository.findAllFaculty(PageRequest.of(page, size));
    }

    public void deleteFaculty(Integer id) {
        Faculty faculty = facultyRepository.findFacultyById(id).orElseThrow(() -> new ItemNotFoundException("Faculty not found with id: %d".formatted(id)));
        faculty.setDeleted(true);
        facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(FacultyUpdateDTO dto) {
        Faculty faculty = getFacultyById(dto.facultyID());
        return facultyRepository.save(FACULTY_MAPPER.partialUpdate(dto, faculty));
    }
}
