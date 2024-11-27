package com.javaweb.studentsmartprintingservice.controller;

import com.javaweb.studentsmartprintingservice.entity.SpsoEntity;
import com.javaweb.studentsmartprintingservice.model.dto.SpsoDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.service.SpsoService;
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
@RequestMapping("/spsos")
public class SpsoController {
    @Autowired
    private SpsoService spsoService;

    @GetMapping
    public ResponseEntity<List<SpsoEntity>> getAllSpsos() {
        List<SpsoEntity> spsos = spsoService.getAll();
        if (spsos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(spsos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpsoEntity> getById(@PathVariable Long id) {
        SpsoEntity spsoEntity = spsoService.getById(id);
        if (spsoEntity == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(spsoEntity);
    }

    @PostMapping
    public ResponseEntity<?> saveSpso(@Valid @RequestBody SpsoDTO spsoDTO, BindingResult bindingResult) {
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
            SpsoEntity spsoEntity = spsoService.save(spsoDTO);
            if (spsoEntity == null) {
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(spsoEntity);
        }
        catch (Exception e){
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteSpso(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (ids.toArray().length == 0) {
                responseDTO.setMessage("Validation failed");
                responseDTO.setDetail(Collections.singletonList("Please select at least one spso to delete"));
                return ResponseEntity.badRequest().body(responseDTO);
            }

            spsoService.delete(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            responseDTO.setMessage("Internal server error");
            responseDTO.setDetail(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
