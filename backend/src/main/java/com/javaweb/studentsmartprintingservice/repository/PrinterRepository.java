package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends JpaRepository<PrinterEntity, Long> {
    void deleteAllByIdIn(List<Long> ids);
    List<PrinterEntity> findAllByIsScheduled(Boolean scheduled);
}
