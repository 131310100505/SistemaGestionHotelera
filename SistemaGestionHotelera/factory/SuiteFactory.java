package SistemaGestionHotelera.factory;

import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.TipoHabitacion;

/** Patron FACTORY METHOD - Fabrica concreta de habitaciones Suite. */
public class SuiteFactory extends HabitacionFactory {

    private static final double PRECIO_BASE = 20000.0;

    @Override
    public Habitacion crearHabitacion(int numero) {
        return new Habitacion(numero, TipoHabitacion.SUITE, PRECIO_BASE);
    }
}
