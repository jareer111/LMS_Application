package com.jareer.lms.app.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentMarkDetailsDTO {
    private String subjectName;
    private List<StudentMarkDTO> studentMarkDTO;
}

