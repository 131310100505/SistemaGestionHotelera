package SistemaGestionHotelera.controller;

import SistemaGestionHotelera.model.Huesped;
import SistemaGestionHotelera.repository.HuespedRepository;

import java.util.List;

/**
 * Controlador de huespedes (GRASP - Controlador).
 * Coordina los casos de uso relacionados con huespedes (CU1).
 * SOLID - SRP: unica responsabilidad, gestionar huespedes.
 */
public class HuespedController {

    private final HuespedRepository repo;

    public HuespedController(HuespedRepository repo) {
        this.repo = repo;
    }

    public Huesped registrarHuesped(int id, String nombre, String dni,
                                    String email, String telefono) {
        Huesped huesped = new Huesped(id, nombre, dni, email, telefono);
        repo.guardar(huesped);
        System.out.println("Huesped registrado: " + huesped.getDatos());
        return huesped;
    }

    public Huesped buscarHuesped(int id) {
        return repo.buscarPorId(id);
    }

    public List<Huesped> listarHuespedes() {
        return repo.listar();
    }
}
