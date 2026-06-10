package SistemaGestionHotelera.factory;

import SistemaGestionHotelera.model.Habitacion;

/**
 * Patron FACTORY METHOD - Creador abstracto.
 * Centraliza la creacion de habitaciones. Cada fabrica concreta decide
 * que tipo de habitacion instanciar, evitando que los controladores
 * dependan de las clases concretas (principio DIP).
 */
public abstract class HabitacionFactory {

    /** Factory Method: las subclases definen como crear la habitacion. */
    public abstract Habitacion crearHabitacion(int numero);
}
