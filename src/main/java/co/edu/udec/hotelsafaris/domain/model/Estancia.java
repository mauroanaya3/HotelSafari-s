package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.exceptions.EstanciaException;
import co.edu.udec.hotelsafaris.domain.valueobjects.EstanciaId;
import java.time.LocalDate;

public class Estancia {
    private final EstanciaId id;
    private Long reservaId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;

    public Estancia(EstanciaId id, Long reservaId, LocalDate fechaInicio, LocalDate fechaFin) {
        if (reservaId == null) throw new EstanciaException("El ID de la reserva no puede ser nulo");
        if (fechaInicio == null || fechaFin == null) throw new EstanciaException("Las fechas no pueden ser nulas");
        if (fechaFin.isBefore(fechaInicio)) throw new EstanciaException("La fecha de fin no puede ser anterior a la de inicio");
        
        this.id = id;
        this.reservaId = reservaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = true;
    }

    // Método de negocio
    public void finalizarEstancia() {
        if (!this.activa) {
            throw new EstanciaException("La estancia ya ha sido finalizada.");
        }
        this.activa = false;
        
    }

    public EstanciaId getId() { return id; }
 
}