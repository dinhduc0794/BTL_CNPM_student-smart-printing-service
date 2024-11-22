package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.SPSOEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPSORepository extends JpaRepository<SPSOEntity, Long> {
}
