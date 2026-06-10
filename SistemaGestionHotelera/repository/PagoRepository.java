package SistemaGestionHotelera.repository;

import SistemaGestionHotelera.model.Pago;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repositorio de pagos (persistencia simulada en memoria).
 */
public class PagoRepository implements Repository<Pago> {

    private final Map<Integer, Pago> datos = new HashMap<>();
    private final DatabaseConnection db = DatabaseConnection.getInstancia();

    @Override
    public void guardar(Pago pago) {
        db.conectar();
        datos.put(pago.getId(), pago);
    }

    @Override
    public Pago buscarPorId(int id) {
        return datos.get(id);
    }

    @Override
    public List<Pago> listar() {
        return new ArrayList<>(datos.values());
    }
}
