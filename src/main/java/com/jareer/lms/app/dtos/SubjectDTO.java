package com.jareer.lms.app.dtos;

import java.util.List;

public record SubjectDTO(String name, Integer groupID, List<Integer> journalID) {
}
