package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.enums.PageSizeEnum;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
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
    public ResponseDTO savePrintingInformation(List<PrintingLogDTO> printingLogDTOs) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<PrintingLogEntity> printingLogEntities = new ArrayList<>();
        for (PrintingLogDTO printingLogDTO : printingLogDTOs) {
            PrintingLogEntity printingLogEntity = modelMapper.map(printingLogDTO, PrintingLogEntity.class);
            if (studentRepository.findById(printingLogDTO.getStudentId()).isEmpty()) {
                responseDTO.setData(printingLogEntities);
                responseDTO.setMessage("Student not found for the given ID: " + printingLogDTO.getStudentId());
                return responseDTO;
            }
            StudentEntity studentEntity = studentRepository.findById(printingLogDTO.getStudentId()).get();

            if (studentEntity.getPaperQuantity() < printingLogEntity.getDocumentPages()) {
                responseDTO.setData(printingLogEntities);
                responseDTO.setMessage("Insufficient paper quantity for the student.");
                return responseDTO;
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

            if (printerRepository.findById(printingLogDTO.getPrinterId()).isEmpty()) {
                responseDTO.setData(printingLogEntities);
                responseDTO.setMessage("Printer not found for the given ID: " + printingLogDTO.getPrinterId());
                return responseDTO;
            }
            PrinterEntity printerEntity = printerRepository.findById(printingLogDTO.getPrinterId()).get();

            if (printerEntity.getPaperA4left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize() == PageSizeEnum.A4
                    || printerEntity.getPaperA3left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize() == PageSizeEnum.A3
                    || printerEntity.getPaperA2left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize() == PageSizeEnum.A2
                    || printerEntity.getPaperA1left() < printingLogEntity.getDocumentPages() && printingLogEntity.getPageSize() == PageSizeEnum.A1) {
                responseDTO.setData(printingLogEntities);
                responseDTO.setMessage("Insufficient paper quantity for the printer.");
                return responseDTO;
            }
            else {
                if (printingLogEntity.getPageSize() == PageSizeEnum.A4) {
                    printerEntity.setPaperA4left(printerEntity.getPaperA4left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize() == PageSizeEnum.A3) {
                    printerEntity.setPaperA3left(printerEntity.getPaperA3left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize() == PageSizeEnum.A2) {
                    printerEntity.setPaperA2left(printerEntity.getPaperA2left() - printingLogEntity.getDocumentPages());
                }
                else if (printingLogEntity.getPageSize() == PageSizeEnum.A1) {
                    printerEntity.setPaperA1left(printerEntity.getPaperA1left() - printingLogEntity.getDocumentPages());
                }
                printerRepository.save(printerEntity);
            }

            printingLogEntity.setStudent(studentEntity);
            printingLogEntity.setPrinter(printerEntity);
            printingLogEntity.setStatus(StatusEnum.PENDING);

            printingLogEntities.add(printingLogEntity);
            printingLogRepository.save(printingLogEntity);
            responseDTO.setData(printingLogEntities);
        }

        responseDTO.setMessage("Printing information saved successfully.");
        return responseDTO;
    }

    @Override
    public void delete(List<Long> ids) {
        printingLogRepository.deleteAllByIdIn(ids);
    }
}
