package com.meeting.rooms.reservations.mapper;

import com.meeting.rooms.reservations.dto.ResponsableDTO;
import com.meeting.rooms.reservations.entity.Responsable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsableMapper {

    ResponsableDTO toDTO(Responsable responsable);

    Responsable toEntity(ResponsableDTO responsableDTO);
}
