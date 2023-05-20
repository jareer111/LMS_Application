package com.jareer.lms.app.dtos;

public record GroupUpdateDTO(
        String name,
        Integer facultyID,
        Integer journalID,
        short year,
        Integer groupID) {
}
