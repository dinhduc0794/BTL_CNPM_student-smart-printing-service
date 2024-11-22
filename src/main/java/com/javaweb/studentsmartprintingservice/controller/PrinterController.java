package com.javaweb.studentsmartprintingservice.controller;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.PrinterService;
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
@RequestMapping("/printers")
public class PrinterController {
    @Autowired
    private PrinterService printerService;

    @GetMapping
    public ResponseEntity<List<PrinterEntity>> getAll() {
        List<PrinterEntity> printers = printerService.getAll();
        if (printers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrinterEntity> getById(@PathVariable Long id) {
        PrinterEntity printerEntity = printerService.getById(id);
        if (printerEntity == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(printerEntity);
    }

    @PostMapping
    public ResponseEntity<?> saveJobPosting(@Valid @RequestBody PrinterDTO printerDTO, BindingResult bindingResult) {
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
            PrinterEntity printerEntity = printerService.save(printerDTO);
            if (printerEntity == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(printerEntity);
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

            printerService.delete(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
