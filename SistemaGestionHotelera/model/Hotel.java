package SistemaGestionHotelera.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de dominio que agrupa las habitaciones y reservas del hotel.
 * GRASP - Experto en Informacion / Creador: conoce sus habitaciones y
 * reservas, por lo que es responsable de listarlas y filtrarlas.
 */
public class Hotel {

    private String nombre;
    private final List<Habitacion> habitaciones = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    public Hotel(String nombre) {
        this.nombre = nombre;
    }

    public void agregarHabitacion(Habitacion h) { habitaciones.add(h); }

    public void agregarReserva(Reserva r) { reservas.add(r); }

    public List<Habitacion> getHabitacionesDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.estaDisponible()) {
                disponibles.add(h);
            }
        }
        return disponibles;
    }

    public String getNombre() { return nombre; }
    public List<Habitacion> getHabitaciones() { return habitaciones; }
    public List<Reserva> getReservas() { return reservas; }
}
