package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Group;
import com.jareer.lms.app.dtos.GroupDTO;
import com.jareer.lms.app.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.GroupMapper.GROUP_MAPPER;


@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group createGroup(GroupDTO dto) {
        Group group = GROUP_MAPPER.toEntity(dto);
        return groupRepository.save(group);
    }

    public Group getGroupById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found with id: %d".formatted(id)));
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(GroupDTO dto, Integer id) {
        Group group = getGroupById(id);
        return groupRepository.save(GROUP_MAPPER.partialUpdate(dto, group));
    }
}
