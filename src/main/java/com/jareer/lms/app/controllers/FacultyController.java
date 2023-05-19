package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.dtos.FacultyDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Faculty>> createFaculty(@Valid FacultyDTO dto) {
        Faculty fakultet = facultyService.createFaculty(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(fakultet));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Faculty>> getFacultyById(@PathVariable Integer id) {
        Faculty fakultet = facultyService.getFacultyById(id);
        return ResponseEntity.ok(new ResponseDTO<>(fakultet));
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseDTO<List<Faculty>>> getAllFaculty() {
        List<Faculty> faculities = facultyService.getAll();
        return ResponseEntity.ok(new ResponseDTO<>(faculities));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Faculty>> updateFaculty(@Valid FacultyDTO dto, Integer id) {
        Faculty fakultet = facultyService.updateFaculty(dto, id);
        return ResponseEntity.status(200).body(new ResponseDTO<>(fakultet));
    }


}

