package com.jareer.lms.app.controllers;

import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.dtos.GroupDTO;
import com.jareer.lms.app.dtos.GroupUpdateDTO;
import com.jareer.lms.app.dtos.ResponseDTO;
import com.jareer.lms.app.services.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO<Group>> createGroup(@RequestBody @Valid GroupDTO dto) {
        Group group = groupService.createGroup(dto);
        return ResponseEntity.status(201).body(new ResponseDTO<>(group));
    }


    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO<Group>> getGroupById(@PathVariable Integer id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(new ResponseDTO<>(group));
    }


    @GetMapping(value = "/getPage")
    public ResponseEntity<ResponseDTO<Page<Group>>> getPageGroup(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                 @RequestParam(required = false, defaultValue = "15") Integer size) {
        Page<Group> groups = groupService.getAll(page, size);
        return ResponseEntity.ok(new ResponseDTO<>(groups));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO<Group>> updateGroup(@RequestBody  @Valid GroupUpdateDTO dto) {
        Group group = groupService.updateGroup(dto);
        return ResponseEntity.status(200).body(new ResponseDTO<>(group));
    }


}

