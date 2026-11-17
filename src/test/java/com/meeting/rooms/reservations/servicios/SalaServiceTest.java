package com.meeting.rooms.reservations.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.dto.SalaDTO;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.repository.ResponsableRepository;
import com.meeting.rooms.reservations.repository.SalaRepository;
import com.meeting.rooms.reservations.service.SalaService;

@ExtendWith(MockitoExtension.class)
class SalaServiceTest {

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private ResponsableRepository responsableRepository;

    @InjectMocks
    private SalaService salaService;

    @Test
    void shouldCreateSalaWithResponsibleAssociations() {
        Responsable responsable = new Responsable();
        responsable.setId(12L);
        responsable.setNombre("María");
        responsable.setApellidos("López");
        responsable.setEdad(26);
        responsable.setSexo("Femenino");
        responsable.setCargo("Gerente");

        ReservaDTO reservaDTO = ReservaDTO.builder()
                .responsableId(12L)
                .salaId(5L)
                .inicio(LocalDateTime.of(2026, 11, 20, 9, 0))
                .fin(LocalDateTime.of(2026, 11, 20, 11, 0))
                .nombreActividad("Taller")
                .build();

        SalaDTO dto = SalaDTO.builder()
                .nombre("Sala de reuniones")
                .capacidad(20)
                .disponibilidad(true)
                .reservas(List.of(reservaDTO))
                .build();

        when(responsableRepository.findById(12L)).thenReturn(java.util.Optional.of(responsable));

        Sala savedSala = new Sala();
        savedSala.setId(5L);
        savedSala.setNombre(dto.getNombre());
        savedSala.setCapacidad(dto.getCapacidad());
        savedSala.setDisponibilidad(dto.getDisponibilidad());

        when(salaRepository.save(any(Sala.class))).thenReturn(savedSala);

        SalaDTO result = salaService.crearSala(dto);

        assertNotNull(result);
        assertEquals("Sala de reuniones", result.getNombre());
        assertEquals(20, result.getCapacidad());
    }
}
