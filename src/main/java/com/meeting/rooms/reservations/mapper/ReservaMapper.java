package com.meeting.rooms.reservations.mapper;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(target = "salaId", source = "sala.id")
    @Mapping(target = "responsableId", source = "responsable.id")
    ReservaDTO toDTO(Reserva reserva);

    @Mapping(target = "sala", ignore = true)
    @Mapping(target = "responsable", ignore = true)
    Reserva toEntity(ReservaDTO reservaDTO);
}
