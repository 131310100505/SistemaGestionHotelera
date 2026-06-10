package SistemaGestionHotelera.strategy;

/**
 * Patron STRATEGY - Estrategia concreta: descuento por temporada baja.
 * Aplica un descuento escalonado: 20% para montos altos, 10% para el resto.
 */
public class DescuentoTemporada implements DescuentoStrategy {

    private static final double UMBRAL = 50000.0;

    @Override
    public double aplicarDescuento(double monto) {
        double porcentaje = (monto >= UMBRAL) ? 20.0 : 10.0;
        return monto - (monto * porcentaje / 100.0);
    }

    @Override
    public String getDescripcion() {
        return "Descuento por temporada baja (10%-20%)";
    }
}
