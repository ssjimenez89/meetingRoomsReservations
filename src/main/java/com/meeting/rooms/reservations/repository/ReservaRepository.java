package com.meeting.rooms.reservations.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.rooms.reservations.entity.Reserva;
import com.meeting.rooms.reservations.entity.Responsable;
import com.meeting.rooms.reservations.entity.Sala;

@Repository 
public interface ReservaRepository extends JpaRepository<Reserva, Long>  {

    //Page findBySalaId(Long salaId, Pageable pageable);

    Page findBySala(Sala sala, Pageable pageable);
    
  
    Page findByInicioAndFin(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    Page findByInicio(LocalDateTime inicio, Pageable pageable);

    Page findByFin(LocalDateTime fin, Pageable pageable);

    Reserva findByResponsable(Responsable responsable);
}
