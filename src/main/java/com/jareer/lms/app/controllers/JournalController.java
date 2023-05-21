package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.dtos.JournalDTO;
import com.jareer.lms.app.dtos.JournalUpdateDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.JournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/journal")
public class JournalController {
    private final JournalService journalService;

    @PreAuthorize("hasAnyAuthority('admin:create','teacher:create')")
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Journal>> createJournal(@RequestBody @Valid JournalDTO dto) {
        Journal journal = journalService.createJournal(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(journal));
    }

    @PreAuthorize("hasAnyAuthority('admin:create','teacher:create')")
    @PostMapping(value = "/addSubject")
    public ResponseEntity<ResponseDTO<Journal>> addSubject(Integer journalId, Integer subjectId) {
        Journal journal = journalService.addSubject(journalId, subjectId);
        return ResponseEntity.ok(new ResponseDTO<>(journal));
    }

    @PreAuthorize("hasAnyAuthority('admin:create','teacher:create')")
    @PostMapping(value = "/addStudent")
    public ResponseEntity<ResponseDTO<Journal>> addStudent(Integer journalId, Integer studentId) {
        Journal journal = journalService.addStudent(journalId, studentId);
        return ResponseEntity.ok(new ResponseDTO<>(journal));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Journal>> getJournalById(@PathVariable Integer id) {
        Journal journal = journalService.getJournalById(id);
        return ResponseEntity.ok(new ResponseDTO<>(journal));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Journal>>> getPageJournal(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Journal> journals = journalService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(journals));
    }


    @PreAuthorize("hasAnyAuthority('admin:delete','teacher:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteJournal(@PathVariable Integer id) {
        journalService.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin:update','teacher:update')")
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Journal>> updateJournal(@RequestBody @Valid JournalUpdateDTO dto) {
        Journal journal = journalService.updateJournal(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(journal));
    }


}

