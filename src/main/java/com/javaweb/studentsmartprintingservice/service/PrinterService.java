package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;

import java.util.List;

public interface PrinterService {
    List<PrinterEntity> getAll();
    List<PrinterEntity> getScheduledPrinters();
    List<PrinterEntity> getNotScheduledPrinters();
    PrinterEntity getById(Long id);
    PrinterEntity save(PrinterDTO printerDTO);
    void delete(List<Long> ids);
}
