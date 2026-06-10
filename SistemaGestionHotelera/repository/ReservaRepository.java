package SistemaGestionHotelera.repository;

import SistemaGestionHotelera.model.Reserva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de reservas (persistencia simulada en memoria).
 * Permite ademas consultar el historial de reservas (RF11).
 */
public class ReservaRepository implements Repository<Reserva> {

    private final Map<Integer, Reserva> datos = new HashMap<>();
    private final DatabaseConnection db = DatabaseConnection.getInstancia();

    @Override
    public void guardar(Reserva reserva) {
        db.conectar();
        datos.put(reserva.getId(), reserva);
    }

    @Override
    public Reserva buscarPorId(int id) {
        return datos.get(id);
    }

    @Override
    public List<Reserva> listar() {
        return new ArrayList<>(datos.values());
    }
}
