package SistemaGestionHotelera.controller;

import SistemaGestionHotelera.factory.HabitacionFactory;
import SistemaGestionHotelera.factory.PremiumFactory;
import SistemaGestionHotelera.factory.StandardFactory;
import SistemaGestionHotelera.factory.SuiteFactory;
import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.TipoHabitacion;
import SistemaGestionHotelera.repository.HabitacionRepository;

import java.util.List;

/**
 * Controlador de habitaciones (GRASP - Controlador).
 * Coordina el registro de habitaciones (CU2) y la consulta de
 * disponibilidad (CU4). Usa el patron Factory Method para instanciar
 * la habitacion segun su tipo, sin acoplarse a las clases concretas.
 */
public class HabitacionController {

    private final HabitacionRepository repo;

    public HabitacionController(HabitacionRepository repo) {
        this.repo = repo;
    }

    /** Selecciona la fabrica adecuada segun el tipo solicitado (Factory Method). */
    private HabitacionFactory obtenerFactory(TipoHabitacion tipo) {
        switch (tipo) {
            case PREMIUM: return new PremiumFactory();
            case SUITE:   return new SuiteFactory();
            default:      return new StandardFactory();
        }
    }

    public Habitacion registrarHabitacion(TipoHabitacion tipo, int numero) {
        HabitacionFactory factory = obtenerFactory(tipo);
        Habitacion habitacion = factory.crearHabitacion(numero);
        repo.guardar(habitacion);
        System.out.println("Habitacion registrada: " + habitacion);
        return habitacion;
    }

    public List<Habitacion> consultarDisponibilidad() {
        return repo.listarDisponibles();
    }

    public Habitacion buscarHabitacion(int numero) {
        return repo.buscarPorId(numero);
    }
}
