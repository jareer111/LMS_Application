package com.jareer.lms.app.services;

import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.domains.user.Role;
import com.jareer.lms.app.dtos.GroupDetailsDTO;
import com.jareer.lms.app.dtos.StudentDetailsDTO;
import com.jareer.lms.app.dtos.StudentMarkDTO;
import com.jareer.lms.app.dtos.StudentMarkDetailsDTO;
import com.jareer.lms.app.enums.UserStatus;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.SubjectRepository;
import com.jareer.lms.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    public List<Subject> getSubjectList(Integer studentID) {
        return subjectRepository.findSubjectsByStudentId(studentID).orElseThrow(() -> new ItemNotFoundException("Subjects not found for student with id: %d".formatted(studentID)));
    }

    public List<GroupDetailsDTO> getFacultyList(Integer facultyId) {
        List<Object[]> objects = groupRepository.findGroupsAndCountStudentsByFacultyId(facultyId).orElseThrow(() -> new ItemNotFoundException("Groups not found for faculty with id: %d".formatted(facultyId)));
        ArrayList<GroupDetailsDTO> groupDetailsDTOS = new ArrayList<>();
        objects.forEach(object -> groupDetailsDTOS.add(new GroupDetailsDTO(String.valueOf(object[0]), (Long) (object[1]))));
        return groupDetailsDTOS;
    }


    public StudentDetailsDTO studentByFullName(String studentFullName) {
        Object[][] object = userRepository.findUserByFullName(studentFullName).orElseThrow(() -> new ItemNotFoundException("Students not found with name: %s".formatted(studentFullName)));
        return new StudentDetailsDTO(
                String.valueOf(object[0][0]),
                Role.valueOf(String.valueOf(object[0][1])),
                UserStatus.valueOf(String.valueOf(object[0][2])),
                String.valueOf(object[0][3]),
                String.valueOf(object[0][4]),
                String.valueOf(object[0][5]));
    }


    public List<StudentMarkDetailsDTO> studentsAndMarksListByGroupId(Integer groupId) {
        List<Object[]> objects = userRepository.findStudentsAndMarksByGroupId(groupId)
                .orElseThrow(() -> new ItemNotFoundException("Students not found for group with id: " + groupId));

        Map<String, List<Object[]>> groupedObjects = objects.stream()
                .collect(Collectors.groupingBy(obj -> String.valueOf(obj[0])));

        return groupedObjects.entrySet().stream()
                .map(entry -> {
                    String subjectName = entry.getKey();
                    List<StudentMarkDTO> studentMarks = entry.getValue().stream()
                            .map(obj -> new StudentMarkDTO(
                                    String.valueOf(obj[1]),
                                    String.valueOf(obj[2])
                            ))
                            .collect(Collectors.toList());
                    return new StudentMarkDetailsDTO(subjectName, studentMarks);
                })
                .collect(Collectors.toList());
    }

}
