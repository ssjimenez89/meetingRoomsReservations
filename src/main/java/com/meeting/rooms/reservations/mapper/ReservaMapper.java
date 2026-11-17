package com.meeting.rooms.reservations.mapper;

import java.util.ArrayList;
import java.util.List;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;


public final class ReservaMapper {

    private ReservaMapper() {
    }

    public static ReservaDTO toDto(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        return ReservaDTO.builder()
                .id(reserva.getId())
                .salaId(reserva.getSala() != null ? reserva.getSala().getId() : null)
                .responsableId(reserva.getResponsable().getId())
                .inicio(reserva.getInicio())
                .fin(reserva.getFin())
                .nombreActividad(reserva.getNombreActividad())
                .estado(reserva.getEstado())
                .build();
    }

    public static Reserva toEntity(ReservaDTO dto, Sala sala, Responsable responsable) {
        if (dto == null) {
            return null;
        }

        Reserva reserva = new Reserva();
        reserva.setId(dto.getId());
        reserva.setSala(sala);
        reserva.setResponsable(responsable);
        reserva.setInicio(dto.getInicio());
        reserva.setFin(dto.getFin());
        reserva.setNombreActividad(dto.getNombreActividad());
        reserva.setEstado(dto.getEstado());
        return reserva;
    }

    public static List<ReservaDTO> toDtos(List<Reserva> reservas) {
        if (reservas == null) {
            return null;
        }

        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            reservasDTO.add(toDto(reserva));            
        }
        return reservasDTO;
    }
}
