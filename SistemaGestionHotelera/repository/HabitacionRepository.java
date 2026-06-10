package SistemaGestionHotelera.repository;

import SistemaGestionHotelera.model.Habitacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de habitaciones (persistencia simulada en memoria).
 */
public class HabitacionRepository implements Repository<Habitacion> {

    private final Map<Integer, Habitacion> datos = new HashMap<>();
    private final DatabaseConnection db = DatabaseConnection.getInstancia();

    @Override
    public void guardar(Habitacion habitacion) {
        db.conectar();
        datos.put(habitacion.getNumero(), habitacion);
    }

    @Override
    public Habitacion buscarPorId(int numero) {
        return datos.get(numero);
    }

    @Override
    public List<Habitacion> listar() {
        return new ArrayList<>(datos.values());
    }

    /** Consulta de disponibilidad (RF4). */
    public List<Habitacion> listarDisponibles() {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion h : datos.values()) {
            if (h.estaDisponible()) {
                disponibles.add(h);
            }
        }
        return disponibles;
    }
}
