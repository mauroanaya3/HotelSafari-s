package co.edu.udec.hotelsafaris.domain.valueobjects;

import java.util.UUID;

public record FacturaId(UUID value) {
    public FacturaId {
        if (value == null) {
            throw new IllegalArgumentException("El ID de la Factura no puede ser nulo");
        }
    }
}