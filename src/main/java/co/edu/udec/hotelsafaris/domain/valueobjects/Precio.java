package co.edu.udec.hotelsafaris.domain.valueobjects;

import java.math.BigDecimal;

public record Precio(BigDecimal monto, String moneda) {
    public Precio {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto del precio no puede ser nulo o negativo");
        }
        if (moneda == null || moneda.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede estar vacía");
        }
    }
}