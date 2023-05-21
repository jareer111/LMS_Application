package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.dtos.SubjectDTO;
import com.jareer.lms.app.dtos.SubjectUpdateDTO;
import com.jareer.lms.app.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasAnyAuthority('admin:create')")
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Subject>> createSubject(@RequestBody @Valid SubjectDTO dto) {
        Subject subject = subjectService.createSubject(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(subject));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Subject>> getSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(new ResponseDTO<>(subject));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Subject>>> getPageSubject(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Subject> subjects = subjectService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(subjects));
    }

    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin:update')")
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Subject>> updateSubject(@RequestBody @Valid SubjectUpdateDTO dto) {
        Subject subject = subjectService.updateSubject(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(subject));
    }


}

