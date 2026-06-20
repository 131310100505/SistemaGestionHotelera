package SistemaGestionHotelera.model.servicio;

/**
 * Patron DECORATOR - Componente.
 * Define interfaz comun para reserva base y sus servicios adicionales,
 * de modo que ambos puedan tratarse de forma uniforme.
 */
public interface ServicioReserva {

    String getDescripcion();

    double getCosto();
}
