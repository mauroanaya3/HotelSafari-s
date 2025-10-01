package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.valueobjects.EstanciaId;
import co.edu.udec.hotelsafaris.domain.valueobjects.FacturaId;
import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;
import java.time.LocalDate;
import java.util.UUID;

public class Factura {
    private final FacturaId id;
    private final EstanciaId estanciaId;
    private final LocalDate fechaEmision;
    private final Precio totalAPagar;

    public Factura(EstanciaId estanciaId, LocalDate fechaEmision, Precio totalAPagar) {
        this.id = new FacturaId(UUID.randomUUID()); 
        this.estanciaId = estanciaId;
        this.fechaEmision = fechaEmision;
        this.totalAPagar = totalAPagar;
    }


    public FacturaId getId() {
        return id;
    }

    public EstanciaId getEstanciaId() {
        return estanciaId;
    }
    
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public Precio getTotalAPagar() {
        return totalAPagar;
    }
}