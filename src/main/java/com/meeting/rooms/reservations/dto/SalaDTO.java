package com.meeting.rooms.reservations.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SalaDTO {

    private Long id;
    private String nombre;
    private Integer capacidad;
    private Boolean disponibilidad;
    private List<ReservaDTO> reservas = new ArrayList<>();
}
