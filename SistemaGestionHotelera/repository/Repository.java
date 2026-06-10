package SistemaGestionHotelera.repository;

import java.util.List;

/**
 * Interfaz generica de persistencia (patron Repository).
 * Desacopla la logica de negocio del mecanismo de almacenamiento.
 * SOLID - ISP / DIP: los controladores dependen de esta abstraccion,
 * no de una implementacion concreta de base de datos.
 *
 * @param <T> tipo de entidad de dominio gestionada.
 */
public interface Repository<T> {

    void guardar(T entidad);

    T buscarPorId(int id);

    List<T> listar();
}
