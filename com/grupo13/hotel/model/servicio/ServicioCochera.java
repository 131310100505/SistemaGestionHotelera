package com.grupo13.hotel.model.servicio;

/** Patron DECORATOR - Decorador concreto: servicio de cochera. */
public class ServicioCochera extends ServicioDecorator {

    private static final double PRECIO = 3000.0;

    public ServicioCochera(ServicioReserva servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return servicio.getDescripcion() + " + Cochera";
    }

    @Override
    public double getCosto() {
        return servicio.getCosto() + PRECIO;
    }
}
