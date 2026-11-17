package com.meeting.rooms.reservations.dto;

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
public class ResponsableDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String sexo;
    private String cargo;
}
