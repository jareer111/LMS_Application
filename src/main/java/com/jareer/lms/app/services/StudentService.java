package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.user.Student;
import com.jareer.lms.app.dtos.StudentDTO;
import com.jareer.lms.app.dtos.StudentUpdateDTO;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.StudentMapper.STUDENT_MAPPER;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepositories;

    public Student createStudent(StudentDTO dto) {
        int groupID = dto.groupID();
        Group group = groupRepositories.findById(groupID).orElseThrow(
                () -> new RuntimeException("Group not found with id: %d".formatted(groupID)));
        Student student = STUDENT_MAPPER.toEntity(dto);
        student.setGroup(group);
        return studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: %d".formatted(id)));
    }

    public Page<Student> getAll(Integer page, Integer size) {
        return studentRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(StudentUpdateDTO dto) {
        Student student = getStudentById(dto.studentID());
        return studentRepository.save(STUDENT_MAPPER.partialUpdate(dto, student));
    }
}
