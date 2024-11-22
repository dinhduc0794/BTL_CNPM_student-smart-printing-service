package com.javaweb.studentsmartprintingservice.converter;

import com.javaweb.studentsmartprintingservice.entity.PrinterEntity;
import com.javaweb.studentsmartprintingservice.entity.SPSOEntity;
import com.javaweb.studentsmartprintingservice.model.dto.PrinterDTO;
import com.javaweb.studentsmartprintingservice.repository.PrinterRepository;
import com.javaweb.studentsmartprintingservice.repository.SPSORepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PrinterConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SPSORepository spsoRepository;

    public PrinterEntity toPrinterEntity(PrinterDTO printerDTO) {
        PrinterEntity printerEntity = modelMapper.map(printerDTO, PrinterEntity.class);

        // Add custom mapping here
        Long spsoId = printerDTO.getSpsoId();
        if (spsoId != null) {
            SPSOEntity spsoEntity = spsoRepository.findById(spsoId).get();
            printerEntity.setSpso(spsoEntity);
        }

        return printerEntity;
    }
}
