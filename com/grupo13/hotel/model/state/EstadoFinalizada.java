package com.grupo13.hotel.model.state;

import com.grupo13.hotel.model.Reserva;

/**
 * Patron STATE - Estado concreto: FINALIZADA (estado final).
 * No admite ninguna transicion.
 */
public class EstadoFinalizada implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        throw new IllegalStateException("No se puede confirmar una reserva finalizada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        throw new IllegalStateException("No se puede cancelar una reserva finalizada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra finalizada.");
    }

    @Override
    public String getNombre() {
        return "FINALIZADA";
    }
}
