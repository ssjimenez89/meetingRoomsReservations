package com.meeting.rooms.reservations.dto;

import java.time.LocalDateTime;

import com.meeting.rooms.reservations.enums.Estado;

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
public class ReservaDTO {

    private Long id;
    private Long salaId;
    private Long responsableId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String nombreActividad;
    private Estado estado;
}
