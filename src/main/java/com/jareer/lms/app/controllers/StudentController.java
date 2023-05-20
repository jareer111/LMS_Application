package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.user.Student;
import com.jareer.lms.app.dtos.StudentDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.StudentUpdateDTO;
import com.jareer.lms.app.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Student>> createStudent(@RequestBody @Valid StudentDTO dto) {
        Student student = studentService.createStudent(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(student));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Student>> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(new ResponseDTO<>(student));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Student>>> getPageStudent(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Student> students = studentService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(students));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Student>> updateStudent(@RequestBody @Valid StudentUpdateDTO dto) {
        Student student = studentService.updateStudent(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(student));
    }


}

