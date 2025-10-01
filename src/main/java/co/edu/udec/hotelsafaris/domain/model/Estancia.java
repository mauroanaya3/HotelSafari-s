package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.exceptions.EstanciaException;
import co.edu.udec.hotelsafaris.domain.valueobjects.EstanciaId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Estancia {
    private final EstanciaId id;
    private final Long reservaId;
    private final LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;
    
    // Objetos que pertenecen a este agregado
    private final List<Habitacion> habitacionesOcupadas;
    private final List<Actividad> actividadesContratadas;

    public Estancia(EstanciaId id, Long reservaId, LocalDate fechaInicio, LocalDate fechaFin, List<Habitacion> habitacionesIniciales) {
        if (reservaId == null) throw new EstanciaException("El ID de la reserva no puede ser nulo");
        if (fechaFin.isBefore(fechaInicio)) throw new EstanciaException("La fecha de fin no puede ser anterior a la de inicio");
        if (habitacionesIniciales == null || habitacionesIniciales.isEmpty()) {
            throw new EstanciaException("Una estancia debe tener al menos una habitación ocupada.");
        }
        
        this.id = id;
        this.reservaId = reservaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = true;
        this.habitacionesOcupadas = new ArrayList<>(habitacionesIniciales);
        this.actividadesContratadas = new ArrayList<>();
    }

    
    public void contratarActividad(Actividad actividad) {
        if (!this.activa) {
            throw new EstanciaException("No se pueden contratar actividades en una estancia ya finalizada.");
        }
        if (actividad == null) {
            throw new EstanciaException("La actividad no puede ser nula.");
        }
        this.actividadesContratadas.add(actividad);
    }
    

    public void finalizarEstancia() {
        if (!this.activa) {
            throw new EstanciaException("La estancia ya ha sido finalizada.");
        }
        this.activa = false;

    }

    
    public EstanciaId getId() { return id; }
    public Long getReservaId() { return reservaId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public boolean isActiva() { return activa; }


    public List<Habitacion> getHabitacionesOcupadas() {
        return Collections.unmodifiableList(habitacionesOcupadas);
    }

    public List<Actividad> getActividadesContratadas() {
        return Collections.unmodifiableList(actividadesContratadas);
    }
}