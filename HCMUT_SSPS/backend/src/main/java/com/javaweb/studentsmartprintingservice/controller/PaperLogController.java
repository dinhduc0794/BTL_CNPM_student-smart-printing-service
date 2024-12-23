package com.javaweb.studentsmartprintingservice.controller;

import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PaperLogDTO;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.PaperLogService;
import com.javaweb.studentsmartprintingservice.service.PrintingLogService;
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
@RequestMapping("/paperlogs")
public class PaperLogController {
    @Autowired
    private PaperLogService paperLogService;

    @GetMapping
    public ResponseEntity<List<PaperLogEntity>> getAllPaperLogs() {
        List<PaperLogEntity> logs = paperLogService.getAll();
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<PaperLogEntity>> getByStudentId(@PathVariable Long id) {
        List<PaperLogEntity> logs = paperLogService.getByStudentId(id);
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaperLogEntity> getById(@PathVariable Long id) {
        PaperLogEntity log = paperLogService.getById(id);
        if (log == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(log);
    }

    @PostMapping("buying-info")
    public ResponseEntity<?> saveBuyingInformation(@Valid @RequestBody PaperLogDTO paperLogDTO, BindingResult bindingResult) {
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
            PaperLogEntity log = paperLogService.saveBuyingInfomation(paperLogDTO);
            if (log == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(log);
        }
        catch (Exception e){
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deletePaperLogs(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (ids.toArray().length == 0) {
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(Collections.singletonList("Please select at least one paper log to delete"));
                return ResponseEntity.badRequest().body(responseDTO);
            }

            paperLogService.delete(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
