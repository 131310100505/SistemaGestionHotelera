package SistemaGestionHotelera.strategy;

/**
 * Patron STRATEGY - Interfaz de estrategia de descuento.
 * Permite intercambiar el algoritmo de calculo de descuento sin
 * modificar la logica de la Reserva (principio Abierto/Cerrado - OCP).
 */
public interface DescuentoStrategy {

    /** Devuelve el monto final luego de aplicar el descuento. */
    double aplicarDescuento(double monto);

    String getDescripcion();
}
