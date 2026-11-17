package com.meeting.rooms.reservations.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.enums.Estado;

public class Utils {

    public static boolean estaOcupadaLaSala(Sala sala, LocalDateTime inicio, LocalDateTime fin) {
        boolean estaOcupadaLaSala = false;
        List<Reserva> reservas = sala.getReservas()
                .stream()
                .filter((reserva) -> reserva.getEstado().equals(Estado.ACTIVA))
                .collect(Collectors.toList());
        for (Reserva reserva : reservas) {
            LocalDateTime inicioTemp = reserva.getInicio();
            LocalDateTime finTemp = reserva.getFin();
            if (inicioTemp.isEqual(inicio) && finTemp.isEqual(fin)) {
                estaOcupadaLaSala = true;
            } else if (inicioTemp.isAfter(inicio) && inicioTemp.isBefore(fin)) {
                estaOcupadaLaSala = true;
            } else if (finTemp.isAfter(inicio) && finTemp.isBefore(fin)) {
                estaOcupadaLaSala = true;
            }
        }
        return estaOcupadaLaSala;
    }

    public static boolean isValidReserva(ReservaDTO reserva) {

        return reserva.getSalaId() != null && reserva.getResponsableId() != null
                && reserva.getInicio() != null && reserva.getFin() != null
                && reserva.getNombreActividad() != null;

    }

    public static Map<String, Object> returnResponse(List<ReservaDTO> reservasDTO, Page<Reserva> pageReservas) {
        Map<String, Object> response = new HashMap<>();
        response.put("reservas", reservasDTO);
        response.put("currentPage", pageReservas.getNumber());
        response.put("totalItems", pageReservas.getTotalElements());
        response.put("totalPages", pageReservas.getTotalPages());
        return response;
    }

}
