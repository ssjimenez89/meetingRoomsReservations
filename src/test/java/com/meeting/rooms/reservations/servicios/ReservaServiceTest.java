package com.meeting.rooms.reservations.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.enums.Estado;
import com.meeting.rooms.reservations.repository.ReservaRepository;
import com.meeting.rooms.reservations.repository.ResponsableRepository;
import com.meeting.rooms.reservations.repository.SalaRepository;
import com.meeting.rooms.reservations.service.ReservaService;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private ResponsableRepository responsableRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void shouldListReservations() {
        Sala sala = new Sala();
        sala.setId(1L);

        Responsable responsable = new Responsable();
        responsable.setId(2L);
        responsable.setNombre("Ana");

        Reserva reserva = new Reserva();
        reserva.setId(10L);
        reserva.setSala(sala);
        reserva.setResponsable(responsable);
        reserva.setInicio(LocalDateTime.of(2026, 11, 18, 9, 0));
        reserva.setFin(LocalDateTime.of(2026, 11, 18, 10, 0));
        reserva.setNombreActividad("Planificación");
        reserva.setEstado(Estado.ACTIVA);

        when(reservaRepository.findAll()).thenReturn(List.of(reserva));

        List<ReservaDTO> result = reservaService.listarReservas();

        assertEquals(1, result.size());
        assertEquals("Planificación", result.get(0).getNombreActividad());
    }

    @Test
    void shouldCreateReservationWhenSalaIsAvailable() {
        Sala sala = new Sala();
        sala.setId(1L);
        sala.setReservas(List.of());

        Responsable responsable = new Responsable();
        responsable.setId(2L);

        ReservaDTO dto = ReservaDTO.builder()
                .salaId(1L)
                .responsableId(2L)
                .inicio(LocalDateTime.of(2026, 11, 18, 12, 0))
                .fin(LocalDateTime.of(2026, 11, 18, 13, 0))
                .nombreActividad("Capacitación")
                .build();

        when(salaRepository.findById(1L)).thenReturn(Optional.of(sala));
        when(responsableRepository.findById(2L)).thenReturn(Optional.of(responsable));
        when(reservaRepository.findByResponsable(responsable)).thenReturn(null);

        Reserva savedReserva = new Reserva();
        savedReserva.setId(99L);
        savedReserva.setSala(sala);
        savedReserva.setResponsable(responsable);
        savedReserva.setInicio(dto.getInicio());
        savedReserva.setFin(dto.getFin());
        savedReserva.setNombreActividad(dto.getNombreActividad());
        savedReserva.setEstado(Estado.ACTIVA);

        when(reservaRepository.save(any(Reserva.class))).thenReturn(savedReserva);

        ReservaDTO result = reservaService.crearReserva(dto);

        assertNotNull(result);
        assertEquals(99L, result.getId());
        assertEquals("Capacitación", result.getNombreActividad());
        verify(reservaRepository).save(any(Reserva.class));
    }
}
