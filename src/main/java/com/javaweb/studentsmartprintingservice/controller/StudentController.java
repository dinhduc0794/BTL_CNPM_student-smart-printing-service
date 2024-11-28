package com.javaweb.studentsmartprintingservice.controller;


import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentLoginDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAll();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getById(@PathVariable Long id) {
        StudentEntity studentEntity = studentService.getById(id);
        if (studentEntity == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(studentEntity);
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());

                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            // neu dung thi //xuong service -> xuong repo -> save vao db
            StudentEntity studentEntity = studentService.save(studentDTO);
            if (studentEntity == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(studentEntity);
        }
        catch (Exception e){
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteStudent(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (ids.toArray().length == 0) {
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(Collections.singletonList("Please select at least one student to delete"));
                return ResponseEntity.badRequest().body(responseDTO);
            }

            studentService.delete(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody StudentDTO studentDTO,
                                        BindingResult result){
        try{
            if(result.hasErrors()){
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if(!studentDTO.getPassword().equals(studentDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password not match");
            }
            StudentEntity studentEntity = studentService.createStudent(studentDTO);//return ResponseEntity.ok("Register successfully");
            return ResponseEntity.ok("");
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); //rule 5
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody StudentLoginDTO loginDTO) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = studentService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
