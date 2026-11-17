package com.meeting.rooms.reservations.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.dto.SalaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;

public final class SalaMapper {

    private SalaMapper() {
    }

    public static SalaDTO toDto(Sala sala) {
        if (sala == null) {
            return null;
        }

        List<ReservaDTO> reservasDto = new ArrayList<>();
        if (sala.getReservas() != null) {
            sala.getReservas().forEach(reserva -> reservasDto.add(ReservaMapper.toDto(reserva)));
        }

        return SalaDTO.builder()
                .id(sala.getId())
                .nombre(sala.getNombre())
                .capacidad(sala.getCapacidad())
                .disponibilidad(sala.getDisponibilidad())
                .reservas(reservasDto)
                .build();
    }

    public static Sala toEntity(SalaDTO dto, Map<Long, Responsable> responsables) {
        if (dto == null) {
            return null;
        }        

        Sala sala = new Sala();
        sala.setId(dto.getId());
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setDisponibilidad(dto.getDisponibilidad());    
        
         List<Reserva> reservas = new ArrayList<>();
        if (dto.getReservas() != null) {
            dto.getReservas().forEach(reservaDto -> {
                Reserva reserva = ReservaMapper.toEntity(reservaDto, sala, responsables.get(reservaDto.getResponsableId()));
                reservas.add(reserva);
            });
        }
        sala.setReservas(reservas);
        return sala;
    }

    public static List<SalaDTO> toDtos(List<Sala> salas) {
        if (salas == null) {
            return null;
        }

        List<SalaDTO> salasDTO = new ArrayList<>();
        for (Sala sala : salas) {
            salasDTO.add(toDto(sala));            
        }
        return salasDTO;
    }

}