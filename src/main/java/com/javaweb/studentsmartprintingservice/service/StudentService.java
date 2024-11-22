package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentEntity> getAll();
    StudentEntity getById(Long id);
    StudentEntity save(StudentDTO studentDTO);
    void delete(List<Long> ids);
}
