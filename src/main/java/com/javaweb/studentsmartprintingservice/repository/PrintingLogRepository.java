package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintingLogRepository extends JpaRepository<PrintingLogEntity, Long> {
}
