package com.jareer.lms.app.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentMarkDetailsDTO {
    private String subjectName;
    private StudentMarkDTO studentMarkDTO;
}

