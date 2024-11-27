package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;

import java.util.List;

public interface PrintingLogService {
    List<PrintingLogEntity> getAll();
    List<PrintingLogEntity> getByStudentId(Long id);
    PrintingLogEntity getById(Long id);
    PrintingLogEntity savePrintingInformation(PrintingLogDTO printingLogDTO);
    void delete(List<Long> ids);
}
