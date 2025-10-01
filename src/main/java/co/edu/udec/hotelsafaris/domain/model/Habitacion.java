package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;

public class Habitacion {
    private final Integer numero;
    private final String tipo;
    private final Precio precioBase;

    public Habitacion(Integer numero, String tipo, Precio precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }


    public Integer getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public Precio getPrecioBase() { return precioBase; }
}