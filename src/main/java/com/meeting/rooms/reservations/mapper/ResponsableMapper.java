package com.meeting.rooms.reservations.mapper;

import java.util.ArrayList;
import java.util.List;

import com.meeting.rooms.reservations.dto.ResponsableDTO;
import com.meeting.rooms.reservations.entity.Responsable;



public final class ResponsableMapper {

    private ResponsableMapper() {
    }

    public static ResponsableDTO toDto(Responsable responsable) {
        if (responsable == null) {
            return null;
        }

        return ResponsableDTO.builder()
                .id(responsable.getId())
                .nombre(responsable.getNombre())
                .apellidos(responsable.getApellidos())
                .edad(responsable.getEdad())
                .sexo(responsable.getSexo())
                .cargo(responsable.getCargo())
                .build();
    }

    public static Responsable toEntity(ResponsableDTO dto) {
        if (dto == null) {
            return null;
        }

        Responsable responsable = new Responsable();
        responsable.setId(dto.getId());
        responsable.setNombre(dto.getNombre());
        responsable.setApellidos(dto.getApellidos());
        responsable.setEdad(dto.getEdad());
        responsable.setSexo(dto.getSexo());
        responsable.setCargo(dto.getCargo());
        return responsable;
    }

    public static List<ResponsableDTO> toDtos(List<Responsable> responsables) {
        if (responsables == null) {
            return null;
        }

        List<ResponsableDTO> responsablesDTO = new ArrayList<>();
        for (Responsable responsable : responsables) {
            responsablesDTO.add(toDto(responsable));
        }
        return responsablesDTO  ;
    }
}
