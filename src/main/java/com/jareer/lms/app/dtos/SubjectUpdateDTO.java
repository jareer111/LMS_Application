package com.jareer.lms.app.dtos;

import java.util.List;

public record SubjectUpdateDTO(String name, Integer groupID, List<Integer> journalID, Integer subjectID) {
}
