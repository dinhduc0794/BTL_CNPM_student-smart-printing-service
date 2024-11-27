package com.javaweb.studentsmartprintingservice.repository;

import com.javaweb.studentsmartprintingservice.entity.SpsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpsoRepository extends JpaRepository<SpsoEntity, Long> {
    void deleteAllByIdIn(List<Long> ids);
}
