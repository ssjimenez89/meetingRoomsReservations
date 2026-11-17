package com.meeting.rooms.reservations.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaDTO {

    private Long id;
    private String nombre;
    private Integer capacidad;
    private Boolean disponibilidad;
    private List<Long> reservasIds;
}
