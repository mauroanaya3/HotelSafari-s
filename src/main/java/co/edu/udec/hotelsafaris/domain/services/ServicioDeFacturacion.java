package co.edu.udec.hotelsafaris.domain.services;

import co.edu.udec.hotelsafaris.domain.exceptions.EstanciaException;
import co.edu.udec.hotelsafaris.domain.model.Estancia;
import co.edu.udec.hotelsafaris.domain.model.Factura;
import co.edu.udec.hotelsafaris.domain.model.Reserva;
import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ServicioDeFacturacion {


    public Factura generarFactura(Estancia estancia, Reserva reserva) {
        
        if (estancia.isActiva()) {
            throw new EstanciaException("No se puede facturar una estancia activa.");
        }
        
        // 1. Calcular costo total de la estancia.
        Precio totalEstancia = calcularCostoTotalEstancia(estancia);
        
        // 2. Obtener el anticipo de la reserva para descontarlo.
        Precio anticipo = reserva.getAnticipo();
        
        // 3. Calcular el valor final a pagar.
        BigDecimal montoFinal = totalEstancia.monto().subtract(anticipo.monto());
        Precio totalAPagar = new Precio(montoFinal, "COP");
        
        // 4. Crear y devolver la nueva entidad Factura.
        return new Factura(estancia.getId(), LocalDate.now(), totalAPagar);
    }


    private Precio calcularCostoTotalEstancia(Estancia estancia) {
        // Suponiendo que el precio base es de $150.000 COP.
        BigDecimal precioPorNoche = new BigDecimal("150000");
        
        // Calcular el número de noches
        long numeroDeNoches = ChronoUnit.DAYS.between(estancia.getFechaInicio(), estancia.getFechaFin());
        if (numeroDeNoches == 0) {
            numeroDeNoches = 1;
        }

        // Calcular costo base.
        BigDecimal costoBase = precioPorNoche.multiply(BigDecimal.valueOf(numeroDeNoches));
        
        
        return new Precio(costoBase, "COP");
    }
}