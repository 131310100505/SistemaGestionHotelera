package SistemaGestionHotelera.strategy;

/**
 * Patron STRATEGY - Estrategia concreta: sin descuento.
 * Estrategia por defecto cuando la reserva no posee promociones.
 */
public class SinDescuento implements DescuentoStrategy {

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }

    @Override
    public String getDescripcion() {
        return "Sin descuento";
    }
}
