package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Student;
import com.jareer.lms.app.dtos.StudentDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Student>> createStudent(@Valid StudentDTO dto) {
        Student student = studentService.createStudent(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(student));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Student>> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(new ResponseDTO<>(student));
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO<List<Student>>> getAllStudent() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(students));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Student>> updateStudent(@Valid StudentDTO dto, Integer id) {
        Student student = studentService.updateStudent(dto, id);
        return ResponseEntity.status(200).body(new ResponseDTO<>(student));
    }


}

