package co.edu.udec.hotelsafaris.domain.factory;

import co.edu.udec.hotelsafaris.domain.enums.EstadoReserva;
import co.edu.udec.hotelsafaris.domain.model.Estancia;
import co.edu.udec.hotelsafaris.domain.model.Habitacion;
import co.edu.udec.hotelsafaris.domain.model.Reserva;
import co.edu.udec.hotelsafaris.domain.valueobjects.EstanciaId;
import java.util.List;
import java.util.UUID;

public class EstanciaFactory {

    public static Estancia crearDesdeReserva(Reserva reserva, List<Habitacion> habitacionesDisponibles) {
        // Validar que la reserva esté en el estado correcto
        if (reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new IllegalArgumentException("Solo se puede crear una estancia desde una reserva confirmada.");
        }
        
        // Validar que se provean las habitaciones
        if (habitacionesDisponibles == null || habitacionesDisponibles.isEmpty()) {
            throw new IllegalArgumentException("Se debe proporcionar al menos una habitación para crear la estancia.");
        }

        // Genera un nuevo ID
        EstanciaId nuevoId = new EstanciaId(UUID.randomUUID());

     
        return new Estancia(
                nuevoId,
                reserva.getId(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                habitacionesDisponibles
        );
    }
}