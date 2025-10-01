package co.edu.udec.hotelsafaris.domain.factory;

import co.edu.udec.hotelsafaris.domain.enums.EstadoReserva;
import co.edu.udec.hotelsafaris.domain.model.Estancia;
import co.edu.udec.hotelsafaris.domain.model.Habitacion;
import co.edu.udec.hotelsafaris.domain.model.Reserva;
import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EstanciaFactoryTest {

    //TEST 1: Probar la regla del estado de la reserva
    @Test
    void debeFallarAlCrearEstanciaDesdeReservaNoConfirmada() {
        // Preparación
        Reserva reservaPendiente = crearReservaDePrueba(EstadoReserva.PENDIENTE);
        List<Habitacion> habitaciones = crearListaDeHabitaciones();

        // Ejecución y Verificación
        assertThatThrownBy(() -> EstanciaFactory.crearDesdeReserva(reservaPendiente, habitaciones))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("reserva confirmada");
    }

    // TEST 2: Probar la regla de la lista de habitaciones
    @Test
    void debeFallarSiLaListaDeHabitacionesEsNulaOVacia() {
        // Arrange
        Reserva reservaConfirmada = crearReservaDePrueba(EstadoReserva.CONFIRMADA);

        // Act & Assert para lista nula
        assertThatThrownBy(() -> EstanciaFactory.crearDesdeReserva(reservaConfirmada, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("proporcionar al menos una habitación");
        
        // Act y Assert para lista vacía
        assertThatThrownBy(() -> EstanciaFactory.crearDesdeReserva(reservaConfirmada, new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("proporcionar al menos una habitación");
    }

    // TEST 3: Probar el "camino feliz" o caso de éxito
    @Test
    void debeCrearEstanciaCorrectamenteConDatosValidos() {
        
        Reserva reservaConfirmada = crearReservaDePrueba(EstadoReserva.CONFIRMADA);
        List<Habitacion> habitaciones = crearListaDeHabitaciones();

        
        Estancia estancia = EstanciaFactory.crearDesdeReserva(reservaConfirmada, habitaciones);

        
        assertThat(estancia).isNotNull();
        assertThat(estancia.getReservaId()).isEqualTo(reservaConfirmada.getId());
        assertThat(estancia.getHabitacionesOcupadas()).hasSize(1);
        assertThat(estancia.isActiva()).isTrue();
    }


    private Reserva crearReservaDePrueba(EstadoReserva estado) {
        Precio anticipo = new Precio(new BigDecimal("50000"), "COP");
        Reserva reserva = new Reserva(1L, anticipo, LocalDate.now(), LocalDate.now().plusDays(5));
        reserva.setEstado(estado);
        return reserva;
    }

    private List<Habitacion> crearListaDeHabitaciones() {
        Precio precioHabitacion = new Precio(new BigDecimal("200000"), "COP");
        Habitacion habitacion = new Habitacion(101, "Doble", precioHabitacion);
        return List.of(habitacion);
    }
}