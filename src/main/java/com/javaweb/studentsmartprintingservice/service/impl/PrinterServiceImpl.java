package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.converter.PrinterConverter;
import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.model.response.ResponseDTO;
import com.javaweb.studentsmartprintingservice.repository.PrinterRepository;
import com.javaweb.studentsmartprintingservice.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterServiceImpl implements PrinterService {
    @Autowired
    private PrinterRepository printerRepository;
    @Autowired
    private PrinterConverter printerConverter;

    @Override
    public List<PrinterEntity> getAll() {
        List<PrinterEntity> printerEntities = printerRepository.findAll();
        return printerEntities;
    }

    @Override
    public PrinterEntity getById(Long id) {
        PrinterEntity printerEntity = printerRepository.findById(id).get();
        return printerEntity;
    }

    @Override
    public PrinterEntity save(PrinterDTO printerDTO) {
        PrinterEntity printerEntity = printerConverter.toPrinterEntity(printerDTO);
        return printerRepository.save(printerEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        printerRepository.deleteAllByIdIn(ids);
    }
}
