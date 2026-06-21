package SistemaGestionHotelera.strategy;

/**
 * Patron STRATEGY - Estrategia concreta: descuento porcentual fijo.
 * Aplica un porcentaje de descuento
 */
public class DescuentoPorcentual implements DescuentoStrategy {

    private final double porcentaje;

    public DescuentoPorcentual(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto - (monto * porcentaje / 100.0);
    }

    @Override
    public String getDescripcion() {
        return "Descuento del " + porcentaje + "%";
    }
}
