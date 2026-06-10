package SistemaGestionHotelera.model.state;

import SistemaGestionHotelera.model.Reserva;

/**
 * Patron STATE - Estado concreto: PENDIENTE.
 * Una reserva pendiente puede confirmarse o cancelarse, pero no finalizarse.
 */
public class EstadoPendiente implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        reserva.setEstado(new EstadoConfirmada());
        System.out.println("  -> Reserva confirmada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new EstadoCancelada());
        System.out.println("  -> Reserva cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        throw new IllegalStateException("No se puede finalizar una reserva que aun esta pendiente.");
    }

    @Override
    public String getNombre() {
        return "PENDIENTE";
    }
}
