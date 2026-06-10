package SistemaGestionHotelera.repository;

import SistemaGestionHotelera.model.Huesped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de huespedes. Implementa la persistencia mediante la
 * unica instancia de {@link DatabaseConnection} (Singleton).
 * En esta version se utiliza almacenamiento en memoria como simulacion.
 */
public class HuespedRepository implements Repository<Huesped> {

    private final Map<Integer, Huesped> datos = new HashMap<>();
    private final DatabaseConnection db = DatabaseConnection.getInstancia();

    @Override
    public void guardar(Huesped huesped) {
        db.conectar();
        datos.put(huesped.getId(), huesped);
    }

    @Override
    public Huesped buscarPorId(int id) {
        return datos.get(id);
    }

    @Override
    public List<Huesped> listar() {
        return new ArrayList<>(datos.values());
    }
}
