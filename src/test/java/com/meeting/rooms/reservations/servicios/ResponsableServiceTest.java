package com.meeting.rooms.reservations.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meeting.rooms.reservations.dto.ResponsableDTO;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.repository.ResponsableRepository;
import com.meeting.rooms.reservations.service.ResponsableService;


@ExtendWith(MockitoExtension.class)
class ResponsableServiceTest {

    @Mock
    private ResponsableRepository responsableRepository;

    @InjectMocks
    private ResponsableService responsableService;

    @Test
    void shouldListResponsables() {
        Responsable responsable = new Responsable();
        responsable.setId(4L);
        responsable.setNombre("Lucía");
        responsable.setApellidos("Santos");
        responsable.setEdad(31);
        responsable.setSexo("Femenino");
        responsable.setCargo("Analista");

        when(responsableRepository.findAll()).thenReturn(List.of(responsable));

        List<ResponsableDTO> result = responsableService.listarResponsables();

        assertEquals(1, result.size());
        assertEquals("Lucía", result.get(0).getNombre());
    }

    @Test
    void shouldCreateResponsable() {
        ResponsableDTO dto = ResponsableDTO.builder()
                .nombre("Pedro")
                .apellidos("Ramírez")
                .edad(28)
                .sexo("Masculino")
                .cargo("Coordinador")
                .build();

        Responsable saved = new Responsable();
        saved.setId(7L);
        saved.setNombre(dto.getNombre());
        saved.setApellidos(dto.getApellidos());
        saved.setEdad(dto.getEdad());
        saved.setSexo(dto.getSexo());
        saved.setCargo(dto.getCargo());

        when(responsableRepository.save(any(Responsable.class))).thenReturn(saved);

        ResponsableDTO result = responsableService.crearResponsable(dto);

        assertNotNull(result);
        assertEquals(7L, result.getId());
        assertEquals("Pedro", result.getNombre());
    }
}
