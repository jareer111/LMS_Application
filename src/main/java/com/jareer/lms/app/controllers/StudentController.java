package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.GroupDetailsDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.StudentDetailsDTO;
import com.jareer.lms.app.dtos.StudentMarkDetailsDTO;
import com.jareer.lms.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/student")
public class StudentController {


    private final StudentService studentService;

    @GetMapping(value = "/subjectLists")
    public ResponseEntity<ResponseDTO<List<Subject>>> getSubjectList(Integer studentID) {
        List<Subject> subjectList = studentService.getSubjectList(studentID);
        return ResponseEntity.status(201).body(new ResponseDTO<>(subjectList));
    }

    @GetMapping(value = "/groupListsByFacultyId/{facultyId}")
    public ResponseEntity<ResponseDTO<List<GroupDetailsDTO>>> getGroupLists(@PathVariable Integer facultyId) {
        List<GroupDetailsDTO> subjectList = studentService.getFacultyList(facultyId);
        return ResponseEntity.status(201).body(new ResponseDTO<>(subjectList));
    }

    @GetMapping(value = "/studentByFullName")
    public ResponseEntity<ResponseDTO<StudentDetailsDTO>> studentByFullName(String studentFullName) {
        StudentDetailsDTO student = studentService.studentByFullName(studentFullName);
        return ResponseEntity.status(201).body(new ResponseDTO<>(student));
    }

    @GetMapping(value = "/studentsAndMarksListByGroupId")
    public ResponseEntity<ResponseDTO<List<StudentMarkDetailsDTO>>> studentsAndMarksListByGroupId(Integer groupId) {
        List<StudentMarkDetailsDTO> markDetailsDTO = studentService.studentsAndMarksListByGroupId(groupId);
        return ResponseEntity.status(201).body(new ResponseDTO<>(markDetailsDTO));
    }

}

