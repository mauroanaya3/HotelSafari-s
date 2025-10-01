package co.edu.udec.hotelsafaris.domain.model;

import co.edu.udec.hotelsafaris.domain.valueobjects.Precio;

public class Actividad {
    private final Integer codigo;
    private final String nombre;
    private final Precio precio;

    public Actividad(Integer codigo, String nombre, Precio precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Precio getPrecio() { return precio; }
}