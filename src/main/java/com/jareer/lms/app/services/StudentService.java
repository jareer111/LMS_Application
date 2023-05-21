package com.jareer.lms.app.services;

import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.Subject;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.JournalRepository;
import com.jareer.lms.app.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final SubjectRepository subjectRepository;
    private final JournalRepository journalRepository;
    private final GroupRepository groupRepository;


    public List<Subject> getSubjectList(Integer studentID) {
        return subjectRepository.findSubjectsByStudentId(studentID).orElseThrow(() -> new ItemNotFoundException("Subjects not found for student with id: %d".formatted(studentID)));
    }

    public Map<Group, Integer> getFacultyList(Integer facultyId) {
        groupRepository.findGroupsAndCountStudentsByFacultyId(facultyId).orElseThrow(() -> new ItemNotFoundException("Groups not found for faculty with id: %d".formatted(facultyId)));
        return null;
    }
}
