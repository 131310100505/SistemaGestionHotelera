package SistemaGestionHotelera.controller;

import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.Huesped;
import SistemaGestionHotelera.model.Reserva;
import SistemaGestionHotelera.repository.ReservaRepository;
import SistemaGestionHotelera.service.Notificador;
import SistemaGestionHotelera.strategy.DescuentoStrategy;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador de reservas (GRASP - Controlador).
 * Coordina los casos de uso de creacion, confirmacion y cancelacion de
 * reservas (CU3, CU5, CU6). Delega el comportamiento de estado en la
 * propia Reserva (patron State) y el calculo del precio en su estrategia.
 *
 * SOLID - DIP: depende de la abstraccion {@link Notificador}, no de una
 * implementacion concreta.
 */
public class ReservaController {

    private final ReservaRepository repo;
    private final Notificador notificador;
    private int secuenciaId = 1;

    public ReservaController(ReservaRepository repo, Notificador notificador) {
        this.repo = repo;
        this.notificador = notificador;
    }

    /** CU3 - Crear reserva. */
    public Reserva crearReserva(Huesped huesped, Habitacion habitacion,
                                LocalDate ingreso, LocalDate salida,
                                DescuentoStrategy descuento) {
        if (!habitacion.estaDisponible()) {
            throw new IllegalStateException("La habitacion " + habitacion.getNumero()
                    + " no esta disponible.");
        }
        Reserva reserva = new Reserva(secuenciaId++, huesped, habitacion, ingreso, salida);
        if (descuento != null) {
            reserva.setDescuento(descuento);
        }
        reserva.calcularCosto();
        habitacion.ocupar();
        repo.guardar(reserva);
        notificador.enviar("Su reserva #" + reserva.getId() + " fue creada (PENDIENTE).",
                huesped.getEmail());
        return reserva;
    }

    /** CU5 - Confirmar reserva (delega en el estado actual). */
    public void confirmarReserva(int idReserva) {
        Reserva reserva = repo.buscarPorId(idReserva);
        validarExistencia(reserva, idReserva);
        reserva.confirmar();
        repo.guardar(reserva);
        notificador.enviar("Su reserva #" + idReserva + " fue confirmada.",
                reserva.getHuesped().getEmail());
    }

    /** CU6 - Cancelar reserva. */
    public void cancelarReserva(int idReserva) {
        Reserva reserva = repo.buscarPorId(idReserva);
        validarExistencia(reserva, idReserva);
        reserva.cancelar();
        reserva.getHabitacion().liberar();
        repo.guardar(reserva);
        notificador.enviar("Su reserva #" + idReserva + " fue cancelada.",
                reserva.getHuesped().getEmail());
    }

    /** Finaliza la estadia (check-out). */
    public void finalizarReserva(int idReserva) {
        Reserva reserva = repo.buscarPorId(idReserva);
        validarExistencia(reserva, idReserva);
        reserva.finalizar();
        reserva.getHabitacion().liberar();
        repo.guardar(reserva);
    }

    /** CU11 - Consultar historial de reservas. */
    public List<Reserva> consultarHistorial() {
        return repo.listar();
    }

    private void validarExistencia(Reserva reserva, int id) {
        if (reserva == null) {
            throw new IllegalArgumentException("No existe la reserva #" + id);
        }
    }
}
