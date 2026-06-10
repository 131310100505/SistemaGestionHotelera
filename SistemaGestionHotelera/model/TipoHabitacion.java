package SistemaGestionHotelera.model;

/**
 * Enumeracion de los tipos de habitacion disponibles en el hotel.
 * Cada tipo posee un multiplicador de precio sobre el precio base.
 */
public enum TipoHabitacion {
    STANDARD(1.0),
    PREMIUM(1.5),
    SUITE(2.2);

    private final double multiplicador;

    TipoHabitacion(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }
}
