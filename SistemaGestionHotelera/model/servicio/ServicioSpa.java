package com.grupo13.hotel.model.servicio;

/** Patron DECORATOR - Decorador concreto: servicio de spa. */
public class ServicioSpa extends ServicioDecorator {

    private static final double PRECIO = 9000.0;

    public ServicioSpa(ServicioReserva servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return servicio.getDescripcion() + " + Spa";
    }

    @Override
    public double getCosto() {
        return servicio.getCosto() + PRECIO;
    }
}
