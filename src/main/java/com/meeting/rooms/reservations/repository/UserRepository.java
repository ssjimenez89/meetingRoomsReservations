package com.meeting.rooms.reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meeting.rooms.reservations.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}