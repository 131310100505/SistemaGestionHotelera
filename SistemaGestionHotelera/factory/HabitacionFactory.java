package SistemaGestionHotelera.factory;

import SistemaGestionHotelera.model.Habitacion;

/**
 * Patron FACTORY METHOD - Creador abstracto.
 * Centraliza creacion de habitaciones. Cada fabrica concreta decide
 * que tipo de habitacion instanciar, evita que los controladores
 * dependan de clases concretas (principio DIP).
 */
public abstract class HabitacionFactory {

    /** Factory Method: las subclases definen como crear la habitacion. */
    public abstract Habitacion crearHabitacion(int numero);
}
