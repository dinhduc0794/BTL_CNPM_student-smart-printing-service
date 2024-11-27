package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    void deleteAllByIdIn(List<Long> ids);
    StudentEntity findByPrintingLogs_Id(Long printingLogId);
}
