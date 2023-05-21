package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


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

    @GetMapping(value = "/groupLists")
    public ResponseEntity<ResponseDTO<Map>> getGroupLists(Integer facultyId) {
        Map<Group, Integer> subjectList = studentService.getFacultyList(facultyId);
        return ResponseEntity.status(201).body(new ResponseDTO<>(subjectList));
    }

}

