package com.meeting.rooms.reservations.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.meeting.rooms.reservations.dto.ReservaDTO;
import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;
import com.meeting.rooms.reservations.enums.Estado;
import com.meeting.rooms.reservations.mapper.ReservaMapper;
import com.meeting.rooms.reservations.repository.ReservaRepository;
import com.meeting.rooms.reservations.repository.ResponsableRepository;
import com.meeting.rooms.reservations.repository.SalaRepository;
import com.meeting.rooms.reservations.utils.Utils;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ResponsableRepository responsableRepository;

    public List<ReservaDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ReservaMapper.toDtos(reservas);
    }

    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        if (Utils.isValidReserva(reservaDTO)) {            
        Sala sala = salaRepository.findById(reservaDTO.getSalaId()).get();
        boolean estaOcupadaLaSala = Utils.estaOcupadaLaSala(sala, reservaDTO.getInicio(), reservaDTO.getFin());
        if (!estaOcupadaLaSala) {            
            Responsable responsable = responsableRepository.findById(reservaDTO.getResponsableId()).get();
            Reserva reservaByResponsable = reservaRepository.findByResponsable(responsable);
            if(Objects.nonNull(reservaByResponsable)) { 
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El responsable ya tiene un Reserva");
            }
            Reserva reserva = ReservaMapper.toEntity(reservaDTO, sala, responsable);
            reserva.setEstado(Estado.ACTIVA);
            Reserva reservaInserted = reservaRepository.save(reserva);
            return ReservaMapper.toDto(reservaInserted);
        }     
        throw new ResponseStatusException(HttpStatus.CONFLICT, "La Sala esta ocupada en este horario");       
    }
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede crear la Reserva, existen campos nulos.");
    }

    public ReservaDTO cancelarReserva(Long id, ReservaDTO reservaDTO) {
        try {
            Optional<Reserva> reservaToUpdateOptional = reservaRepository.findById(id);
            if (reservaToUpdateOptional.isPresent()) {
                Reserva reservaToUpdate = reservaToUpdateOptional.get();
                reservaToUpdate.setEstado(Estado.CANCELADA);
                Reserva reservaCanceled = reservaRepository.save(reservaToUpdate);
                return ReservaMapper.toDto(reservaCanceled);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada.");
        } catch (Exception ex) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public Map<String, Object> findBySala(Sala sala, Pageable paging) {

        try {
            List<Reserva> reservas = new ArrayList<Reserva>();

            Page<Reserva> pageReservas;
            if (sala == null)
                pageReservas = reservaRepository.findAll(paging);
            else
                pageReservas = reservaRepository.findBySala(sala, paging);

            reservas = pageReservas.getContent();
            List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
            reservasDTO = ReservaMapper.toDtos(reservas);
          
            Map<String, Object> response = Utils.returnResponse(reservasDTO, pageReservas);
            return response;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public Map<String, Object> findByInicioAndFin(String from, String to, Pageable paging) {

        try {
            List<Reserva> reservas = new ArrayList<Reserva>();

            Page<Reserva> pageReservas;
            if (from == null || to == null)
                pageReservas = reservaRepository.findAll(paging);
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                pageReservas = reservaRepository.findByInicioAndFin(LocalDateTime.from(formatter.parse(from)),
                LocalDateTime.from(formatter.parse(to)), paging);
            }

            reservas = pageReservas.getContent();
            List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
            reservasDTO = ReservaMapper.toDtos(reservas);
            
            Map<String, Object> response = Utils.returnResponse(reservasDTO, pageReservas);
            return response;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public Map<String, Object> findByInicioPage(String inicio, Pageable paging) {

        try {
            List<Reserva> reservas = new ArrayList<Reserva>();

            Page<Reserva> pageReservas;
            if (inicio == null)
                pageReservas = reservaRepository.findAll(paging);
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                LocalDateTime.from(formatter.parse(inicio));
                pageReservas = reservaRepository.findByInicio(LocalDateTime.from(formatter.parse(inicio)), paging);
            }

            reservas = pageReservas.getContent();
            List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
            reservasDTO = ReservaMapper.toDtos(reservas);

             Map<String, Object> response = Utils.returnResponse(reservasDTO, pageReservas);
            return response;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public Map<String, Object> findByFinPage(String fin, Pageable paging) {

        try {
            List<Reserva> reservas = new ArrayList<Reserva>();

            Page<Reserva> pageReservas;
            if (fin == null)
                pageReservas = reservaRepository.findAll(paging);
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                pageReservas = reservaRepository.findByFin(LocalDateTime.from(formatter.parse(fin)), paging);
            }

            reservas = pageReservas.getContent();
            List<ReservaDTO> reservasDTO = new ArrayList<ReservaDTO>();
            reservasDTO = ReservaMapper.toDtos(reservas);

             Map<String, Object> response = Utils.returnResponse(reservasDTO, pageReservas);
            return response;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
