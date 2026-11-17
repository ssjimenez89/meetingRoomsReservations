package com.meeting.rooms.reservations.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.enums.Estado;

class UtilsTest {

    @Test
    void shouldReturnFalseWhenSalaHasNoActiveReservations() {
        Sala sala = new Sala();
        sala.setReservas(List.of());

        boolean result = Utils.estaOcupadaLaSala(
                sala,
                LocalDateTime.of(2026, 11, 18, 9, 0),
                LocalDateTime.of(2026, 11, 18, 10, 0)
        );

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenExistingReservationStartsInsideCandidateWindow() {
        Sala sala = new Sala();
        Reserva reserva = new Reserva();
        reserva.setInicio(LocalDateTime.of(2026, 11, 18, 10, 0));
        reserva.setFin(LocalDateTime.of(2026, 11, 18, 11, 0));
        reserva.setEstado(Estado.ACTIVA);
        sala.setReservas(List.of(reserva));

        boolean result = Utils.estaOcupadaLaSala(
                sala,
                LocalDateTime.of(2026, 11, 18, 9, 0),
                LocalDateTime.of(2026, 11, 18, 10, 30)
        );

        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenExistingReservationEndsInsideCandidateWindow() {
        Sala sala = new Sala();
        Reserva reserva = new Reserva();
        reserva.setInicio(LocalDateTime.of(2026, 11, 18, 9, 0));
        reserva.setFin(LocalDateTime.of(2026, 11, 18, 11, 0));
        reserva.setEstado(Estado.ACTIVA);
        sala.setReservas(List.of(reserva));

        boolean result = Utils.estaOcupadaLaSala(
                sala,
                LocalDateTime.of(2026, 11, 18, 10, 30),
                LocalDateTime.of(2026, 11, 18, 12, 0)
        );

        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenReservaHasAllRequiredFields() {
        ReservaDTO reservaDTO = ReservaDTO.builder()
                .salaId(1L)
                .responsableId(2L)
                .inicio(LocalDateTime.of(2026, 11, 18, 9, 0))
                .fin(LocalDateTime.of(2026, 11, 18, 10, 0))
                .nombreActividad("Reunión")
                .build();

        boolean result = Utils.isValidReserva(reservaDTO);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenARequiredFieldIsNull() {
        ReservaDTO reservaDTO = ReservaDTO.builder()
                .salaId(1L)
                .responsableId(2L)
                .inicio(LocalDateTime.of(2026, 11, 18, 9, 0))
                .fin(LocalDateTime.of(2026, 11, 18, 10, 0))
                .nombreActividad(null)
                .build();

        boolean result = Utils.isValidReserva(reservaDTO);

        assertFalse(result);
    }
}
