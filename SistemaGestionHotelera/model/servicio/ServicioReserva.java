package com.grupo13.hotel.model.servicio;

/**
 * Patron DECORATOR - Componente.
 * Define la interfaz comun para la reserva base y sus servicios adicionales,
 * de modo que ambos puedan tratarse de forma uniforme.
 */
public interface ServicioReserva {

    String getDescripcion();

    double getCosto();
}
