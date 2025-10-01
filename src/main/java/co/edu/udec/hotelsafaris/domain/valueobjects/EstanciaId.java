package co.edu.udec.hotelsafaris.domain.valueobjects;

import java.util.UUID;

public record EstanciaId(UUID value) {
    public EstanciaId {
        if (value == null) {
            throw new IllegalArgumentException("El ID de la Estancia no puede ser nulo");
        }
    }
}