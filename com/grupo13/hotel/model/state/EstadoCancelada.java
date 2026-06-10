package com.grupo13.hotel.model.state;

import com.grupo13.hotel.model.Reserva;

/**
 * Patron STATE - Estado concreto: CANCELADA (estado final).
 * No admite ninguna transicion.
 */
public class EstadoCancelada implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        throw new IllegalStateException("No se puede confirmar una reserva cancelada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new IllegalStateException("No se puede finalizar una reserva cancelada.");
    }

    @Override
    public String getNombre() {
        return "CANCELADA";
    }
}
