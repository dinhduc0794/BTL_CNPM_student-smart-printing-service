package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.repository.StudentRepository;
import com.javaweb.studentsmartprintingservice.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public StudentEntity createStudent(StudentDTO studentDTO) throws Exception {
        //register user
        String username = studentDTO.getUsername();
        if(studentRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        //convert from userDTO => userEntity
        StudentEntity newUser = modelMapper.map(studentDTO, StudentEntity.class);

        // Kiểm tra nếu có accountId, không yêu cầu password
        String password = studentDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return studentRepository.save(newUser);
    }

    @Override
    public String login(String username, String password) throws Exception {
        Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(username);
        if(optionalStudent.isEmpty()) {
            throw new DataIntegrityViolationException("Invalid username / password");
        }

        StudentEntity existingStudent = optionalStudent.get();

        if(!passwordEncoder.matches(password, existingStudent.getPassword())) {
            throw new BadCredentialsException("Wrong phone number or password");
        }

        return "Login success";
    }
}
