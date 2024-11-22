package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.repository.StudentRepository;
import com.javaweb.studentsmartprintingservice.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities;
    }

    @Override
    public StudentEntity getById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).get();
        return studentEntity;
    }

    @Override
    public StudentEntity save(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        return studentRepository.save(studentEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        studentRepository.deleteAllByIdIn(ids);
    }
}
