package SistemaGestionHotelera.controller;

import SistemaGestionHotelera.model.Pago;
import SistemaGestionHotelera.model.Reserva;
import SistemaGestionHotelera.repository.PagoRepository;

/**
 * Controlador pagos (GRASP - Controlador).
 * Coordina registro de pagos asociados a reserva (CU10).
 */
public class PagoController {

    private final PagoRepository repo;
    private int secuenciaId = 1;

    public PagoController(PagoRepository repo) {
        this.repo = repo;
    }

    /** CU10 - Registrar pago de una reserva. */
    public Pago registrarPago(Reserva reserva, String metodoPago) {
        Pago pago = new Pago(secuenciaId++, reserva.getCostoTotal(), metodoPago);
        reserva.setPago(pago);
        repo.guardar(pago);
        System.out.println("Pago registrado: " + pago);
        return pago;
    }
}
