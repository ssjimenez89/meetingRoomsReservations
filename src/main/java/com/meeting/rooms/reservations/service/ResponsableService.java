package com.meeting.rooms.reservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeting.rooms.reservations.dto.ResponsableDTO;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.mapper.ResponsableMapper;
import com.meeting.rooms.reservations.repository.ResponsableRepository;

@Service        
public class ResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    public List<ResponsableDTO> listarResponsables() {
         List<Responsable> responsables = responsableRepository.findAll();
        return ResponsableMapper.toDtos(responsables);
    }
        

    public ResponsableDTO crearResponsable(ResponsableDTO responsableDTO) {
        Responsable responsable = ResponsableMapper.toEntity(responsableDTO);
        Responsable responsableInserted = responsableRepository.save(responsable);
        return ResponsableMapper.toDto(responsableInserted);
    }

}