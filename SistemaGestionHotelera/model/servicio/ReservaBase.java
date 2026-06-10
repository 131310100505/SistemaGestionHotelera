package com.grupo13.hotel.model.servicio;

/**
 * Patron DECORATOR - Componente concreto.
 * Representa el alojamiento base (sin servicios adicionales) sobre el cual
 * se iran "envolviendo" los servicios adicionales.
 */
public class ReservaBase implements ServicioReserva {

    private final double costoAlojamiento;

    public ReservaBase(double costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }

    @Override
    public String getDescripcion() {
        return "Alojamiento";
    }

    @Override
    public double getCosto() {
        return costoAlojamiento;
    }
}
