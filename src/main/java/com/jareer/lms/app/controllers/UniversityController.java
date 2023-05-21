package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.UniversityDTO;
import com.jareer.lms.app.dtos.UniversityUpdateDTO;
import com.jareer.lms.app.services.UniversityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<University>> createUniversity(@RequestBody @Valid UniversityDTO dto) {
        University university = universityService.createUniversity(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(university));
    }



    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<University>> getUniversityById(@PathVariable Integer id) {
        University university = universityService.getUniversityById(id);
        return ResponseEntity.ok(new ResponseDTO<>(university));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<University>>> getPageUniversity(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                           @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<University> universities = universityService.getPage(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(universities));
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteUniversity(@PathVariable Integer id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<University>> updateUniversity(@RequestBody @Valid UniversityUpdateDTO dto) {
        University university = universityService.updateUniversity(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(university));
    }


}

