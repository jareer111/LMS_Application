package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.dtos.GroupDTO;
import com.jareer.lms.app.dtos.GroupUpdateDTO;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import com.jareer.lms.app.repositories.FacultyRepository;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.jareer.lms.app.mappers.GroupMapper.GROUP_MAPPER;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;


    public Group createGroup(GroupDTO dto) {
        int facultyID = dto.facultyID();

        Faculty faculty = facultyRepository.findFacultyById(facultyID).orElseThrow(
                () -> new ItemNotFoundException("Faculty not found with id: %d".formatted(facultyID)));

        Group group = GROUP_MAPPER.toEntity(dto);
        group.setFaculty(faculty);
        return groupRepository.save(group);
    }

    public Group getGroupById(Integer id) {
        return groupRepository.findGroupById(id).orElseThrow(() -> new ItemNotFoundException("Group not found with id: %d".formatted(id)));
    }

    public Page<Group> getAll(Integer page, Integer size) {
        return groupRepository.findAllGroup(PageRequest.of(page, size));
    }

    public void deleteGroup(Integer id) {
        Group group = groupRepository.findGroupById(id).orElseThrow(() -> new ItemNotFoundException("Group not found with id: %d".formatted(id)));
        group.setDeleted(true);
        groupRepository.save(group);
    }

    public Group updateGroup(GroupUpdateDTO dto) {
        Group group = getGroupById(dto.groupID());
        return groupRepository.save(GROUP_MAPPER.partialUpdate(dto, group));
    }

    public User addStudent(Integer groupID, Integer studentID) {
        Group group = groupRepository.findGroupById(groupID).orElseThrow(() -> new ItemNotFoundException("Group not found with id: %d".formatted(groupID)));
        User student = userRepository.findById(studentID).orElseThrow(() -> new ItemNotFoundException("Student not found with id: %d".formatted(studentID)));
        student.setGroup(group);
        return userRepository.save(student);
    }
}
