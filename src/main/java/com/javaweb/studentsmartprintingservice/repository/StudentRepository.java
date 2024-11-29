package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void deleteAllByIdIn(List<Long> ids);
    StudentEntity findByPrintingLogs_Id(Long printingLogId);
    Optional<StudentEntity> findByUsername(String username);
}
