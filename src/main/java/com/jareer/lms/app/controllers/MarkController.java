package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Mark;
import com.jareer.lms.app.dtos.MarkDTO;
import com.jareer.lms.app.dtos.MarkUpdateDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.MarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/mark")
public class MarkController {
    private final MarkService markService;

    @PreAuthorize("hasAnyAuthority('admin:create','teacher:create')")
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Mark>> createMark(@RequestBody @Valid MarkDTO dto) {
        Mark mark = markService.createMark(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(mark));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Mark>> getMarkById(@PathVariable Integer id) {
        Mark mark = markService.getMarkById(id);
        return ResponseEntity.ok(new ResponseDTO<>(mark));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Mark>>> getPageMark(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                               @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Mark> mark = markService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(mark));
    }

    @PreAuthorize("hasAnyAuthority('admin:delete','teacher:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteMark(@PathVariable Integer id) {
        markService.deleteMark(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('admin:update','teacher:update')")
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Mark>> updateMark(@RequestBody @Valid MarkUpdateDTO dto) {
        Mark mark = markService.updateMark(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(mark));
    }


}

