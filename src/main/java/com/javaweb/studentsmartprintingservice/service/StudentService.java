package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface StudentService {
    List<StudentEntity> getAll();
    StudentEntity getById(Long id);
    StudentEntity save(StudentDTO studentDTO);
    void delete(List<Long> ids);
    StudentEntity createStudent(StudentDTO studentDTO) throws Exception;
    ResponseDTO login(String username, String password) throws Exception;
}
