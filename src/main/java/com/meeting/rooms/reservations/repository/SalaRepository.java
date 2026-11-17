package com.meeting.rooms.reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.rooms.reservations.entity.Sala;

@Repository 
public interface SalaRepository extends JpaRepository<Sala, Long>    {


}