package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.repository.PrintingLogRepository;
import com.javaweb.studentsmartprintingservice.repository.StudentRepository;
import com.javaweb.studentsmartprintingservice.service.PrintingLogService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PrintingLogServiceImpl implements PrintingLogService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrintingLogRepository printingLogRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<PrintingLogEntity> getAll(){
        List<PrintingLogEntity> printingLogEntities = printingLogRepository.findAll();
        return printingLogEntities;
    }

    @Override
    public List<PrintingLogEntity> getByStudentId(Long id) {
        List<PrintingLogEntity> printingLogEntities = printingLogRepository.findAllByStudent_Id(id);
        return printingLogEntities;
    }

    @Override
    public PrintingLogEntity getById(Long id) {
        PrintingLogEntity printingLogEntity = printingLogRepository.findById(id).get();
        return printingLogEntity;
    }

    @Override
    public PrintingLogEntity savePrintingInformation(@RequestBody PrintingLogDTO printingLogDTO) {
        PrintingLogEntity printingLogEntity = modelMapper.map(printingLogDTO, PrintingLogEntity.class);
        StudentEntity studentEntity = studentRepository.findById(printingLogDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found for the given ID: " + printingLogDTO.getStudentId()));

        if (studentEntity.getPaperQuantity() < printingLogEntity.getDocumentPages()) {
            throw new IllegalArgumentException("Insufficient paper quantity for the student.");
        }

        // Update student's paper quantity & some other fields
        studentEntity.getPrintingLogs().add(printingLogEntity);
        studentEntity.setIs2Side(printingLogEntity.getIs2Side());
        studentEntity.setIsColor(printingLogEntity.getIsColor());
        studentEntity.setPageSize(printingLogEntity.getPageSize());
        studentEntity.setPaperQuantity(studentEntity.getPaperQuantity() - printingLogEntity.getDocumentPages());


        studentRepository.save(studentEntity);

        printingLogEntity.setStudent(studentEntity);
        printingLogEntity.setStatus(StatusEnum.PENDING);
        return printingLogRepository.save(printingLogEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        printingLogRepository.deleteAllByIdIn(ids);
    }
}
