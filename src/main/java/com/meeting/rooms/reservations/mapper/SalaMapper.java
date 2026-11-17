package com.meeting.rooms.reservations.mapper;

import com.meeting.rooms.reservations.dto.SalaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Sala;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaMapper {

    @Mapping(
        target = "reservasIds",
        expression = "java(sala.getReservas() == null ? null : sala.getReservas().stream().map(Reserva::getId).collect(Collectors.toList()))"
    )
    SalaDTO toDTO(Sala sala);

    @Mapping(target = "reservas", ignore = true)
    Sala toEntity(SalaDTO salaDTO);
}
