package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperLogRepository extends JpaRepository<PaperLogEntity, Long> {
    void deleteAllByIdIn(List<Long> logIds);
    List<PaperLogEntity> findAllByStudent_Id(Long id);
}
