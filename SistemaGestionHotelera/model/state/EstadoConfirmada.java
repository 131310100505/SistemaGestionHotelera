package SistemaGestionHotelera.model.state;

import SistemaGestionHotelera.model.Reserva;

/**
 * Patron STATE - Estado concreto: CONFIRMADA.
 * Una reserva confirmada puede finalizarse (check-out) o cancelarse.
 */
public class EstadoConfirmada implements EstadoReserva {

    @Override
    public void confirmar(Reserva reserva) {
        throw new IllegalStateException("La reserva ya se encuentra confirmada.");
    }

    @Override
    public void cancelar(Reserva reserva) {
        reserva.setEstado(new EstadoCancelada());
        System.out.println("  -> Reserva confirmada cancelada.");
    }

    @Override
    public void finalizar(Reserva reserva) {
        reserva.setEstado(new EstadoFinalizada());
        System.out.println("  -> Estadia finalizada (check-out).");
    }

    @Override
    public String getNombre() {
        return "CONFIRMADA";
    }
}
