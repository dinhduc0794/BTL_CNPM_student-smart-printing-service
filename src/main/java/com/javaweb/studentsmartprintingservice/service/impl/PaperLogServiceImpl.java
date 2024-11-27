package com.javaweb.studentsmartprintingservice.service.impl;

import com.javaweb.studentsmartprintingservice.constant.SystemConstant;
import com.javaweb.studentsmartprintingservice.entity.PaperLogEntity;
import com.javaweb.studentsmartprintingservice.entity.StudentEntity;
import com.javaweb.studentsmartprintingservice.enums.StatusEnum;
import com.javaweb.studentsmartprintingservice.model.dto.PaperLogDTO;
import com.javaweb.studentsmartprintingservice.repository.PaperLogRepository;
import com.javaweb.studentsmartprintingservice.repository.StudentRepository;
import com.javaweb.studentsmartprintingservice.service.PaperLogService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperLogServiceImpl implements PaperLogService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaperLogRepository paperLogRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<PaperLogEntity> getAll(){
        List<PaperLogEntity> paperLogEntities = paperLogRepository.findAll();
        return paperLogEntities;
    }

    @Override
    public List<PaperLogEntity> getByStudentId(Long id) {
        List<PaperLogEntity> paperLogEntities = paperLogRepository.findAllByStudent_Id(id);
        return paperLogEntities;
    }

    @Override
    public PaperLogEntity getById(Long id) {
        PaperLogEntity paperLogEntity = paperLogRepository.findById(id).get();
        return paperLogEntity;
    }

    @Override
    public PaperLogEntity saveBuyingInfomation(PaperLogDTO paperLogDTO) {
        PaperLogEntity paperLogEntity = modelMapper.map(paperLogDTO, PaperLogEntity.class);

        StudentEntity studentEntity = studentRepository.findById(paperLogDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found for the given ID: " + paperLogDTO.getStudentId()));

//        studentEntity.setPaperQuantity(studentEntity.getPaperQuantity() + paperLogDTO.getQuantity());
        paperLogEntity.setTotalPrice(paperLogDTO.getQuantity() * SystemConstant.UNIT_PRICE);

        studentRepository.save(studentEntity);

        paperLogEntity.setStudent(studentEntity);
        paperLogEntity.setStatus(StatusEnum.PENDING);
        return paperLogRepository.save(paperLogEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        paperLogRepository.deleteAllByIdIn(ids);
    }
}
