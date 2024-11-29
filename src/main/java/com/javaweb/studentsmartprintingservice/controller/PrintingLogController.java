package com.javaweb.studentsmartprintingservice.controller;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.model.dto.StudentDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
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
@RequestMapping("/printinglogs")
public class PrintingLogController {
    @Autowired
    private PrintingLogService printingLogService;

    @GetMapping
    public ResponseEntity<List<PrintingLogEntity>> getAllPrintingLogs() {
        List<PrintingLogEntity> logs = printingLogService.getAll();
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<PrintingLogEntity>> getByStudentId(@PathVariable Long id) {
        List<PrintingLogEntity> logs = printingLogService.getByStudentId(id);
        if (logs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrintingLogEntity> getById(@PathVariable Long id) {
        PrintingLogEntity log = printingLogService.getById(id);
        if (log == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(log);
    }

    @PostMapping("printing-info")
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

    @DeleteMapping("/{ids}")
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
}
