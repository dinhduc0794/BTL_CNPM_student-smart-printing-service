package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperLogRepository extends JpaRepository<PaperLogEntity, Long> {
}
