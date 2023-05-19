package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.SubjectDTO;
import com.jareer.lms.app.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Subject>> createSubject(@Valid SubjectDTO dto) {
        Subject subject = subjectService.createSubject(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(subject));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Subject>> getSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(new ResponseDTO<>(subject));
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO<List<Subject>>> getAllSubject() {
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(subjects));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Subject>> updateSubject(@Valid SubjectDTO dto, Integer id) {
        Subject subject = subjectService.updateSubject(dto, id);
        return ResponseEntity.status(200).body(new ResponseDTO<>(subject));
    }


}

