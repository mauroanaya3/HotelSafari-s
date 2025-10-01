package co.edu.udec.hotelsafaris.domain.events;

import co.edu.udec.hotelsafaris.domain.valueobjects.EstanciaId;
import java.time.Instant;

public record EstanciaFinalizadaEvent(EstanciaId estanciaId, Instant fechaFinalizacion) {
    public EstanciaFinalizadaEvent {
        if (estanciaId == null || fechaFinalizacion == null) {
            throw new IllegalArgumentException("Los datos del evento no pueden ser nulos");
        }
    }
}