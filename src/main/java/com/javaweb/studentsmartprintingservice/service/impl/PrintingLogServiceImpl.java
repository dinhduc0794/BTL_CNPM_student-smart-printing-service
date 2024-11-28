package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.repository.PrinterRepository;
import com.javaweb.studentsmartprintingservice.repository.PrintingLogRepository;
import com.javaweb.studentsmartprintingservice.repository.StudentRepository;
import com.javaweb.studentsmartprintingservice.service.PrintingLogService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrintingLogServiceImpl implements PrintingLogService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PrintingLogRepository printingLogRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PrinterRepository printerRepository;

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
    public List<PrintingLogEntity> savePrintingInformation(List<PrintingLogDTO> printingLogDTOs) {
        List<PrintingLogEntity> printingLogEntities = new ArrayList<>();
        for (PrintingLogDTO printingLogDTO : printingLogDTOs) {
            PrintingLogEntity printingLogEntity = modelMapper.map(printingLogDTO, PrintingLogEntity.class);
            StudentEntity studentEntity = studentRepository.findById(printingLogDTO.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found for the given ID: " + printingLogDTO.getStudentId()));

            if (studentEntity.getPaperQuantity() < printingLogEntity.getDocumentPages()) {
                throw new IllegalArgumentException("Insufficient paper quantity for the student.");
            }
            else {
                // Update student's paper quantity & some other fields
                studentEntity.getPrintingLogs().add(printingLogEntity);
                studentEntity.setIs2Side(printingLogEntity.getIs2Side());
                studentEntity.setIsColor(printingLogEntity.getIsColor());
                studentEntity.setPageSize(printingLogEntity.getPageSize());
                studentEntity.setPaperQuantity(studentEntity.getPaperQuantity() - printingLogEntity.getDocumentPages());
                studentRepository.save(studentEntity);
            }

            PrinterEntity printerEntity = printerRepository.findById(printingLogDTO.getPrinterId())
                    .orElseThrow(() -> new EntityNotFoundException("Printer not found for the given ID: " + printingLogDTO.getPrinterId()));
            if (printerEntity.getPaperA4left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize().equals("A4")
                    || printerEntity.getPaperA3left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize().equals("A3")
                    || printerEntity.getPaperA2left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize().equals("A2")
                    || printerEntity.getPaperA1left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize().equals("A1")) {
                throw new IllegalArgumentException("Insufficient paper quantity for the printer.");
            }
            else {
                if (printingLogEntity.getPageSize().equals("A4")) {
                    printerEntity.setPaperA4left(printerEntity.getPaperA4left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize().equals("A3")) {
                    printerEntity.setPaperA3left(printerEntity.getPaperA3left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize().equals("A2")) {
                    printerEntity.setPaperA2left(printerEntity.getPaperA2left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize().equals("A1")) {
                    printerEntity.setPaperA1left(printerEntity.getPaperA1left() - printingLogEntity.getDocumentPages());
                }
                printerRepository.save(printerEntity);
            }

            printingLogEntity.setStudent(studentEntity);
            printingLogEntity.setPrinter(printerEntity);
            printingLogEntity.setStatus(StatusEnum.PENDING);
            printingLogRepository.save(printingLogEntity);

            printingLogEntities.add(printingLogEntity);
        }

        return printingLogEntities;
    }

    @Override
    public void delete(List<Long> ids) {
        printingLogRepository.deleteAllByIdIn(ids);
    }
}
