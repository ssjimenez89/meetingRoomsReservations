package com.meeting.rooms.reservations.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nombre")
    private String nombre;

    @Column(nullable = false, name = "capacidad")
    private Integer capacidad;

    @Column(nullable = false, name = "disponibilidad")
    private Boolean disponibilidad;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("sala")
    private List<Reserva> reservas = new ArrayList<>();

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.setSala(this);
    }

    public void removeReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.setSala(null);
    }
}
