package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PaperLogDTO;

import java.util.List;

public interface PaperLogService {
    List<PaperLogEntity> getAll();
    List<PaperLogEntity> getByStudentId(Long id);
    PaperLogEntity getById(Long id);
    PaperLogEntity saveBuyingInfomation(PaperLogDTO paperLogDTO);
    void delete(List<Long> ids);
}
