package SistemaGestionHotelera.model;

import SistemaGestionHotelera.model.servicio.ReservaBase;
import SistemaGestionHotelera.model.servicio.ServicioReserva;
import SistemaGestionHotelera.model.state.EstadoPendiente;
import SistemaGestionHotelera.model.state.EstadoReserva;
import SistemaGestionHotelera.strategy.DescuentoStrategy;
import SistemaGestionHotelera.strategy.SinDescuento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase de dominio central. Representa una reserva de habitacion.
 *
 * Integra tres patrones de comportamiento/estructurales:
 *  - STATE   : el campo {@code estado} delega las transiciones validas.
 *  - STRATEGY: el campo {@code descuento} define la politica de descuento.
 *  - DECORATOR: el campo {@code servicios} acumula servicios adicionales.
 *
 * GRASP - Experto en Informacion: la Reserva calcula su propio costo
 * porque es quien conoce habitacion, fechas, servicios y descuento.
 */
public class Reserva {

    private int id;
    private Huesped huesped;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private EstadoReserva estado;
    private DescuentoStrategy descuento;
    private ServicioReserva servicios;   // cadena de decoradores (Decorator)
    private Promocion promocion;
    private Pago pago;
    private double costoTotal;

    public Reserva(int id, Huesped huesped, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaSalida) {
        this.id = id;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = new EstadoPendiente();          // estado inicial (State)
        this.descuento = new SinDescuento();           // estrategia por defecto (Strategy)
        this.servicios = new ReservaBase(calcularAlojamiento()); // componente base (Decorator)
    }

    // ---------- Transiciones de estado (delegadas al patron State) ----------

    public void confirmar() { estado.confirmar(this); }

    public void cancelar() { estado.cancelar(this); }

    public void finalizar() { estado.finalizar(this); }

    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    // ---------- Servicios adicionales (Decorator) ----------

    /** Envuelve la cadena actual de servicios con un nuevo decorador. */
    public void agregarServicio(ServicioReserva servicioDecorado) {
        this.servicios = servicioDecorado;
    }

    public ServicioReserva getServicios() { return servicios; }

    // ---------- Descuentos (Strategy) ----------

    public void setDescuento(DescuentoStrategy descuento) {
        this.descuento = descuento;
    }

    // ---------- Calculo de costo (Experto en Informacion) ----------

    private long getNoches() {
        long noches = ChronoUnit.DAYS.between(fechaIngreso, fechaSalida);
        return Math.max(noches, 1);
    }

    private double calcularAlojamiento() {
        return habitacion.getPrecioPorNoche() * getNoches();
    }

    /**
     * Calcula el costo total combinando alojamiento + servicios (Decorator)
     * y aplicando luego la estrategia de descuento (Strategy).
     */
    public double calcularCosto() {
        double base = servicios.getCosto();
        this.costoTotal = descuento.aplicarDescuento(base);
        return costoTotal;
    }

    public void aplicarPromocion(Promocion p) {
        this.promocion = p;
    }

    // ---------- Getters ----------

    public int getId() { return id; }
    public Huesped getHuesped() { return huesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public EstadoReserva getEstado() { return estado; }
    public Promocion getPromocion() { return promocion; }
    public Pago getPago() { return pago; }
    public void setPago(Pago pago) { this.pago = pago; }
    public double getCostoTotal() { return costoTotal; }

    @Override
    public String toString() {
        return "Reserva #" + id + " - " + huesped.getNombre()
                + " | Hab " + habitacion.getNumero()
                + " | " + fechaIngreso + " a " + fechaSalida
                + " | Estado: " + estado.getNombre()
                + " | Total: $" + costoTotal;
    }
}
