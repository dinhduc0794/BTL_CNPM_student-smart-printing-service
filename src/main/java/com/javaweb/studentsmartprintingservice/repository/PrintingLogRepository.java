package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrintingLogRepository extends JpaRepository<PrintingLogEntity, Long> {
    void deleteAllByIdIn(List<Long> logIds);
    List<PrintingLogEntity> findAllByStudent_Id(Long id);
}
