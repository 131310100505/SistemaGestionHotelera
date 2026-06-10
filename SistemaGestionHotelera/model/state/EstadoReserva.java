package SistemaGestionHotelera.model.state;

import SistemaGestionHotelera.model.Reserva;

/**
 * Patron STATE - Interfaz de estado.
 * Define el comportamiento de una reserva segun su estado actual.
 * Cada estado decide si una transicion es valida o no.
 */
public interface EstadoReserva {

    void confirmar(Reserva reserva);

    void cancelar(Reserva reserva);

    void finalizar(Reserva reserva);

    String getNombre();
}
