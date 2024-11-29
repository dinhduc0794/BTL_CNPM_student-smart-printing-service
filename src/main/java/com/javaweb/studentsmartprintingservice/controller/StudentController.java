package com.javaweb.studentsmartprintingservice.controller;


import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.*;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.PaperLogService;
import com.javaweb.studentsmartprintingservice.service.PrinterService;
import com.javaweb.studentsmartprintingservice.service.PrintingLogService;
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
    @Autowired
    private PrintingLogService printingLogService;
    @Autowired
    private PrinterService printerService;
    @Autowired
    private PaperLogService paperLogService;

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

    @GetMapping("/printinglogs")
    public ResponseEntity<List<PrintingLogEntity>> getAllPrintingLogs() {
        List<PrintingLogEntity> logs = printingLogService.getAll();
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}/printinglogs")
    public ResponseEntity<List<PrintingLogEntity>> getByPrintingLogStudentId(@PathVariable Long id) {
        List<PrintingLogEntity> logs = printingLogService.getByStudentId(id);
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/printinglogs/{id}")
    public ResponseEntity<PrintingLogEntity> getPrintingLogById(@PathVariable Long id) {
        PrintingLogEntity log = printingLogService.getById(id);
        if (log == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(log);
    }

    @PostMapping("/printinglogs/printing-info")
    public ResponseEntity<?> savePrintingInformation(@Valid @RequestBody List<PrintingLogDTO> printingLogDTOs, BindingResult bindingResult) {
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
            responseDTO = printingLogService.savePrintingInformation(printingLogDTOs);
            if (responseDTO.getData() instanceof List<?>) {
                List<?> dataList = (List<?>) responseDTO.getData();
                if (dataList.size() != printingLogDTOs.size()) {
                    return ResponseEntity.ok().body(responseDTO);
                }
            } else {
                // Xử lý trường hợp responseDTO.getData() không phải là List
                return ResponseEntity.badRequest().body(responseDTO);
            }
            return ResponseEntity.ok(responseDTO);
        }
        catch (Exception e){
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping("printinglogs/{ids}")
    public ResponseEntity<?> deletePrintingLogs(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (ids.toArray().length == 0) {
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(Collections.singletonList("Please select at least one printing log to delete"));
                return ResponseEntity.badRequest().body(responseDTO);
            }

            printingLogService.delete(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @GetMapping("/printers")
    public ResponseEntity<List<PrinterEntity>> getAllPrinters() {
        List<PrinterEntity> printers = printerService.getAll();
        if (printers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/printers/scheduled")
    public ResponseEntity<List<PrinterEntity>> getScheduledPrinters() {
        List<PrinterEntity> printers = printerService.getScheduledPrinters();
        if (printers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/printers/not-scheduled")
    public ResponseEntity<List<PrinterEntity>> getNotScheduledPrinters() {
        List<PrinterEntity> printers = printerService.getNotScheduledPrinters();
        if (printers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/printers/{id}")
    public ResponseEntity<PrinterEntity> getByPrinterId(@PathVariable Long id) {
        PrinterEntity printerEntity = printerService.getById(id);
        if (printerEntity == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printerEntity);
    }

    @GetMapping("/paperlogs")
    public ResponseEntity<List<PaperLogEntity>> getAllPaperLogs() {
        List<PaperLogEntity> logs = paperLogService.getAll();
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}/paperlogs")
    public ResponseEntity<List<PaperLogEntity>> getByPaperLogStudentId(@PathVariable Long id) {
        List<PaperLogEntity> logs = paperLogService.getByStudentId(id);
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/paperlogs/{id}")
    public ResponseEntity<PaperLogEntity> getPaperLogById(@PathVariable Long id) {
        PaperLogEntity log = paperLogService.getById(id);
        if (log == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(log);
    }

    @PostMapping("/paperlogs/buying-info")
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

    @DeleteMapping("/paperlogs/{ids}")
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
