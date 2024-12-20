package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.SpsoEntity;
import com.javaweb.studentsmartprintingservice.model.dto.SpsoDTO;

import java.util.List;

public interface SpsoService {
    List<SpsoEntity> getAll();
    SpsoEntity getById(Long id);
    SpsoEntity save(SpsoDTO spsoDTO);
    void delete(List<Long> ids);
}
