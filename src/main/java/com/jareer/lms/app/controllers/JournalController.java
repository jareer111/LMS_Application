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
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/journal")
public class JournalController {
    private final JournalService journalService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Journal>> createJournal(@RequestBody @Valid JournalDTO dto) {
        Journal fakultet = journalService.createJournal(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(fakultet));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Journal>> getJournalById(@PathVariable Integer id) {
        Journal fakultet = journalService.getJournalById(id);
        return ResponseEntity.ok(new ResponseDTO<>(fakultet));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Journal>>> getPageJournal(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Journal> journals = journalService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(journals));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteJournal(@PathVariable Integer id) {
        journalService.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Journal>> updateJournal(@RequestBody @Valid JournalUpdateDTO dto) {
        Journal fakultet = journalService.updateJournal(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(fakultet));
    }


}

