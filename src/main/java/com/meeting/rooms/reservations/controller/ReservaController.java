package com.meeting.rooms.reservations.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.service.ReservaService;

@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaService;    

    @GetMapping("/api/listar/reservas")
    public List<ReservaDTO> listarReservas() {
        return reservaService.listarReservas();
    }

    @PostMapping("/api/reservas")
    public ReservaDTO crearReserva(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.crearReserva(reservaDTO);
    }

    @PutMapping("/api/reservas/{id}")
    public ReservaDTO cancelarReserva(@PathVariable Long id,
            @RequestBody ReservaDTO reservaDTO) {
        return reservaService.cancelarReserva(id, reservaDTO);
    }

    @GetMapping("/api/listar/paging/reservas/sala")
    public Map<String, Object> findBySala(
            @RequestParam(required = false) Sala sala,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservaService.findBySala(sala, paging);
    }    

     @GetMapping("/api/listar/paging/reservas/fecha")
    public Map<String, Object> findByInicioAndFin(
            @RequestParam(required = false) String from,
             @RequestParam(required = false) String   to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservaService.findByInicioAndFin(from, to, paging);
    }
    
    @GetMapping("/api/listar/paging/reservas/inicio")
    public Map<String, Object> findByInicioPage(
            @RequestParam(required = false) String inicio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservaService.findByInicioPage(inicio, paging);
    }

     @GetMapping("/api/listar/paging/reservas/fin")
    public Map<String, Object> findByFinPage(
            @RequestParam(required = false) String fin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservaService.findByFinPage(fin, paging);
    }
}