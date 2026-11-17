package com.meeting.rooms.reservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.rooms.reservations.dto.ResponsableDTO;
import com.meeting.rooms.reservations.service.ResponsableService;

@RestController 
public class ResponsableController {

    @Autowired 
    private ResponsableService responsableService;
    
    
    @GetMapping ("/api/listar/responsables")
    public List<ResponsableDTO> listarResponsables() {
        return responsableService.listarResponsables();
    }
    
    @PostMapping ("/api/responsables")
    public ResponsableDTO crearResponsable(@RequestBody ResponsableDTO responsableDTO) {
        return responsableService.crearResponsable(responsableDTO);
    }

}