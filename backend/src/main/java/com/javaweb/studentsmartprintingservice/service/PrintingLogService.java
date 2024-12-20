package com.javaweb.studentsmartprintingservice.service;

import com.javaweb.studentsmartprintingservice.entity.PrintingLogEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrintingLogDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;

import java.util.List;

public interface PrintingLogService {
    List<PrintingLogEntity> getAll();
    List<PrintingLogEntity> getByStudentId(Long id);
    PrintingLogEntity getById(Long id);
    ResponseDTO savePrintingInformation(List<PrintingLogDTO> printingLogDTOs);
    void delete(List<Long> ids);
}
