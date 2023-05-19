package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.UniversityDTO;
import com.jareer.lms.app.services.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<University>> createUniversity(@Valid UniversityDTO dto) {
        University university = universityService.createUniversity(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(university));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<University>> getUniversityById(@PathVariable Integer id) {
        University university = universityService.getUniversityById(id);
        return ResponseEntity.ok(new ResponseDTO<>(university));
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO<List<University>>> getAllUniversity() {
        List<University> universities = universityService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(universities));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteUniversity(@PathVariable Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<University>> updateUniversity(@Valid UniversityDTO dto, Integer id) {
        University university = universityService.updateUniversity(dto, id);
        return ResponseEntity.status(200).body(new ResponseDTO<>(university));
    }


}

