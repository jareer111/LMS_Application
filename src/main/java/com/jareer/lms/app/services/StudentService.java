package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Student;
import com.jareer.lms.app.dtos.StudentDTO;
import com.jareer.lms.app.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.StudentMapper.STUDENT_MAPPER;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student createStudent(StudentDTO dto) {
        Student student = STUDENT_MAPPER.toEntity(dto);
        return studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: %d".formatted(id)));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(StudentDTO dto, Integer id) {
        Student student = getStudentById(id);
        return studentRepository.save(STUDENT_MAPPER.partialUpdate(dto, student));
    }
}
