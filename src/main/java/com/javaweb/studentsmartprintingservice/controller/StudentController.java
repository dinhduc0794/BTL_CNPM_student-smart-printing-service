package com.javaweb.studentsmartprintingservice.controller;


import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.PrinterService;
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
    public ResponseEntity<List<StudentEntity>> getAll() {
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
    public ResponseEntity<?> saveJobPosting(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrinter(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (ids.toArray().length == 0) {
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(Collections.singletonList("Please select at least one building to delete"));
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
}
