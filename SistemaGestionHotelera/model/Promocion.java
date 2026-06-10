package SistemaGestionHotelera.model;

/**
 * Clase de dominio que representa una promocion aplicable a una reserva.
 */
public class Promocion {

    private String nombre;
    private double porcentajeDescuento;

    public Promocion(String nombre, double porcentajeDescuento) {
        this.nombre = nombre;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getNombre() { return nombre; }
    public double getPorcentajeDescuento() { return porcentajeDescuento; }

    @Override
    public String toString() {
        return "Promocion '" + nombre + "' (" + porcentajeDescuento + "%)";
    }
}
