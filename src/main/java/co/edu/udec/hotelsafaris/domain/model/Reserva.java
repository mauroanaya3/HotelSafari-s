package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.enums.EstadoReserva;
import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;
import java.time.LocalDate;

public class Reserva {
    private final Long id;
    private EstadoReserva estado;
    private final Precio anticipo;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public Reserva(Long id, Precio anticipo, LocalDate fechaInicio, LocalDate fechaFin) {

        this.id = id;
        this.anticipo = anticipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoReserva.PENDIENTE; // Estado inicial
    }

    public void confirmar() {
        this.estado = EstadoReserva.CONFIRMADA;
    }
    
    public void cancelar() {
        this.estado = EstadoReserva.CANCELADA;
    }


    public Long getId() {
        return id;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public Precio getAnticipo() {
        return anticipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    
    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}