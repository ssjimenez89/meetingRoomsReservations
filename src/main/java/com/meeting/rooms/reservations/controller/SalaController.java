package com.meeting.rooms.reservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.meeting.rooms.reservations.dto.SalaDTO;
import com.meeting.rooms.reservations.service.SalaService;

@RestController 
public class SalaController {

    @Autowired 
    private SalaService salaService;

        @GetMapping("/api/listar/salas")
        public List<SalaDTO> listarSalas() {
            return salaService.listarSalas();       
        }       

        @PostMapping("/api/salas")
        public SalaDTO crearSala(@RequestBody SalaDTO salaDTO) {
            return salaService.crearSala(salaDTO);
        }
}
