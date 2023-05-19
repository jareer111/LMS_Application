package com.jareer.lms.app.services;


import com.jareer.lms.app.domains.Mark;
import com.jareer.lms.app.dtos.MarkDTO;
import com.jareer.lms.app.repositories.MarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jareer.lms.app.mappers.MarkMapper.MARK_MAPPER;


@Service
@RequiredArgsConstructor
public class MarkService {
    private final MarkRepository markRepository;

    public Mark createMark(MarkDTO dto) {
        Mark mark = MARK_MAPPER.toEntity(dto);
        return markRepository.save(mark);
    }

    public Mark getMarkById(Integer id) {
        return markRepository.findById(id).orElseThrow(() -> new RuntimeException("Mark not found with id: %d".formatted(id)));
    }

    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    public void deleteMark(Integer id) {
        markRepository.deleteById(id);
    }

    public Mark updateMark(MarkDTO dto, Integer id) {
        Mark mark = getMarkById(id);
        return markRepository.save(MARK_MAPPER.partialUpdate(dto, mark));
    }
}
