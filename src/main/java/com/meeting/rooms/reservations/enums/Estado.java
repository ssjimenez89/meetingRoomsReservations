package com.meeting.rooms.reservations.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estado de las reservas")
public enum Estado {   

    ACTIVA,
    EJECUTADA,
    CANCELADA
    
}
