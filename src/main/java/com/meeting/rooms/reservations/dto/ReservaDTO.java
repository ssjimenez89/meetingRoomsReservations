package com.meeting.rooms.reservations.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {

    private Long id;
    private Long salaId;
    private Long responsableId;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String nombreActividad;
}
