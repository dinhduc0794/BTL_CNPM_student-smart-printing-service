package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.SpsoEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.repository.PrinterRepository;
import com.javaweb.studentsmartprintingservice.repository.SpsoRepository;
import com.javaweb.studentsmartprintingservice.service.PrinterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterServiceImpl implements PrinterService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PrinterRepository printerRepository;
    @Autowired
    private SpsoRepository spsoRepository;

    @Override
    public List<PrinterEntity> getAll() {
        List<PrinterEntity> printerEntities = printerRepository.findAll();
        return printerEntities;
    }

    @Override
    public List<PrinterEntity> getScheduledPrinters() {
        List<PrinterEntity> printerEntities = printerRepository.findAllByIsScheduled(true);
        return printerEntities;
    }

    @Override
    public List<PrinterEntity> getNotScheduledPrinters() {
        List<PrinterEntity> printerEntities = printerRepository.findAllByIsScheduled(false);
        return printerEntities;
    }

    @Override
    public PrinterEntity getById(Long id) {
        PrinterEntity printerEntity = printerRepository.findById(id).get();
        return printerEntity;
    }

    @Override
    public PrinterEntity save(PrinterDTO printerDTO) {
        PrinterEntity printerEntity = modelMapper.map(printerDTO, PrinterEntity.class);

        // Add custom mapping here
        Long spsoId = printerDTO.getSpsoId();
        if (spsoId != null) {
            SpsoEntity spsoEntity = spsoRepository.findById(spsoId).get();
            printerEntity.setSpso(spsoEntity);
        }

        return printerRepository.save(printerEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        printerRepository.deleteAllByIdIn(ids);
    }
}
