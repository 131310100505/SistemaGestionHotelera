package SistemaGestionHotelera.facade;

import SistemaGestionHotelera.controller.HabitacionController;
import SistemaGestionHotelera.controller.HuespedController;
import SistemaGestionHotelera.controller.PagoController;
import SistemaGestionHotelera.controller.ReservaController;
import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.Huesped;
import SistemaGestionHotelera.model.Pago;
import SistemaGestionHotelera.model.Reserva;
import SistemaGestionHotelera.model.TipoHabitacion;
import SistemaGestionHotelera.repository.HabitacionRepository;
import SistemaGestionHotelera.repository.HuespedRepository;
import SistemaGestionHotelera.repository.PagoRepository;
import SistemaGestionHotelera.repository.ReservaRepository;
import SistemaGestionHotelera.service.EmailNotificador;
import SistemaGestionHotelera.service.Notificador;
import SistemaGestionHotelera.strategy.DescuentoStrategy;

import java.time.LocalDate;
import java.util.List;

/**
 * Patron FACADE.
 * Ofrece una interfaz unica y simplificada para operar el sistema hotelero,
 * ocultando la complejidad de coordinar los distintos controladores,
 * repositorios y patrones internos.
 * GRASP - Bajo Acoplamiento: el cliente (Main) interactua solo con la
 * fachada y no con cada subsistema por separado.
 */
public class SistemaHotelFacade {

    private final HuespedController huespedCtrl;
    private final HabitacionController habitacionCtrl;
    private final ReservaController reservaCtrl;
    private final PagoController pagoCtrl;

    public SistemaHotelFacade() {
        // Inicializacion de la capa de persistencia
        HuespedRepository huespedRepo = new HuespedRepository();
        HabitacionRepository habitacionRepo = new HabitacionRepository();
        ReservaRepository reservaRepo = new ReservaRepository();
        PagoRepository pagoRepo = new PagoRepository();

        Notificador notificador = new EmailNotificador();

        // Inicializacion de los controladores
        this.huespedCtrl = new HuespedController(huespedRepo);
        this.habitacionCtrl = new HabitacionController(habitacionRepo);
        this.reservaCtrl = new ReservaController(reservaRepo, notificador);
        this.pagoCtrl = new PagoController(pagoRepo);
    }

    // ---------- Operaciones de alto nivel ----------

    public Huesped registrarHuesped(int id, String nombre, String dni,
                                    String email, String telefono) {
        return huespedCtrl.registrarHuesped(id, nombre, dni, email, telefono);
    }

    public Habitacion registrarHabitacion(TipoHabitacion tipo, int numero) {
        return habitacionCtrl.registrarHabitacion(tipo, numero);
    }

    public List<Habitacion> consultarDisponibilidad() {
        return habitacionCtrl.consultarDisponibilidad();
    }

    public Reserva realizarReserva(int idHuesped, int numeroHabitacion,
                                   LocalDate ingreso, LocalDate salida,
                                   DescuentoStrategy descuento) {
        Huesped huesped = huespedCtrl.buscarHuesped(idHuesped);
        Habitacion habitacion = habitacionCtrl.buscarHabitacion(numeroHabitacion);
        return reservaCtrl.crearReserva(huesped, habitacion, ingreso, salida, descuento);
    }

    public void confirmarReserva(int idReserva) {
        reservaCtrl.confirmarReserva(idReserva);
    }

    public void cancelarReserva(int idReserva) {
        reservaCtrl.cancelarReserva(idReserva);
    }

    public void finalizarReserva(int idReserva) {
        reservaCtrl.finalizarReserva(idReserva);
    }

    public Pago registrarPago(Reserva reserva, String metodoPago) {
        return pagoCtrl.registrarPago(reserva, metodoPago);
    }

    public List<Reserva> consultarHistorial() {
        return reservaCtrl.consultarHistorial();
    }

    public Reserva buscarReserva(int idReserva) {
        for (Reserva r : consultarHistorial()) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        throw new IllegalArgumentException("No existe la reserva #" + idReserva);
    }

    public void agregarServicioAdicional(int idReserva, String tipoServicio) {
        Reserva reserva = buscarReserva(idReserva);

        switch (tipoServicio) {
            case "Desayuno":
                reserva.agregarServicio(new SistemaGestionHotelera.model.servicio.ServicioDesayuno(reserva.getServicios()));
                break;

            case "Spa":
                reserva.agregarServicio(new SistemaGestionHotelera.model.servicio.ServicioSpa(reserva.getServicios()));
                break;

            case "Cochera":
                reserva.agregarServicio(new SistemaGestionHotelera.model.servicio.ServicioCochera(reserva.getServicios()));
                break;

            default:
                throw new IllegalArgumentException("Servicio inválido.");
        }

        reserva.calcularCosto();
    }

    public Pago registrarPago(int idReserva, String metodoPago) {
        Reserva reserva = buscarReserva(idReserva);
        return registrarPago(reserva, metodoPago);
    }
}
