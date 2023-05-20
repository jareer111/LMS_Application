package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Faculty;
import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.domains.Journal;
import com.jareer.lms.app.domains.University;
import com.jareer.lms.app.dtos.GroupDTO;
import com.jareer.lms.app.dtos.GroupUpdateDTO;
import com.jareer.lms.app.repositories.FacultyRepository;
import com.jareer.lms.app.repositories.GroupRepository;
import com.jareer.lms.app.repositories.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.GroupMapper.GROUP_MAPPER;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final JournalRepository journalRepository;


    public Group createGroup(GroupDTO dto) {
        int facultyID = dto.facultyID();
        int journalID = dto.journalID();

        Faculty faculty = facultyRepository.findById(facultyID).orElseThrow(
                () -> new RuntimeException("Faculty not found with id: %d".formatted(facultyID)));
        Journal journal = journalRepository.findById(journalID).orElseThrow(
                () -> new RuntimeException("Journal not found with id: %d".formatted(journalID)));

        Group group = GROUP_MAPPER.toEntity(dto);
        group.setFaculty(faculty);
        group.setJournal(journal);
        return groupRepository.save(group);
    }

    public Group getGroupById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found with id: %d".formatted(id)));
    }

    public Page<Group> getAll(Integer page, Integer size) {
        return groupRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(GroupUpdateDTO dto) {
        Group group = getGroupById(dto.groupID());
        return groupRepository.save(GROUP_MAPPER.partialUpdate(dto, group));
    }
}
