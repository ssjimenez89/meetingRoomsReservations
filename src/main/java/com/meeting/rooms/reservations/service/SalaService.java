package com.meeting.rooms.reservations.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.dto.SalaDTO;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.mapper.SalaMapper;
import com.meeting.rooms.reservations.repository.ResponsableRepository;
import com.meeting.rooms.reservations.repository.SalaRepository;

@Service 
public class SalaService {

    @Autowired 
    private SalaRepository salaRepository;
    @Autowired 
    private ResponsableRepository responsableRepository;
    
    public List<SalaDTO> listarSalas() {
        List<Sala> salas = salaRepository.findAll();
        return SalaMapper.toDtos(salas);
    }
    
    public SalaDTO crearSala(SalaDTO salaDTO) {
        List<ReservaDTO> reservaDTOs = salaDTO.getReservas();
        Map<Long, Responsable> responsables = new HashMap<>();

        for(ReservaDTO reserva : reservaDTOs) {
            Long id = reserva.getResponsableId();
            responsables.put(id, responsableRepository.findById(reserva.getResponsableId()).get());
        }
        Sala sala = SalaMapper.toEntity(salaDTO, responsables);
        Sala salaInserted = salaRepository.save(sala);
        return SalaMapper.toDto(salaInserted);
    }   

}

