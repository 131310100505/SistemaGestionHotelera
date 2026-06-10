package SistemaGestionHotelera.main;

import SistemaGestionHotelera.facade.SistemaHotelFacade;
import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.Reserva;
import SistemaGestionHotelera.model.TipoHabitacion;
import SistemaGestionHotelera.model.servicio.ReservaBase;
import SistemaGestionHotelera.model.servicio.ServicioCochera;
import SistemaGestionHotelera.model.servicio.ServicioDesayuno;
import SistemaGestionHotelera.model.servicio.ServicioReserva;
import SistemaGestionHotelera.model.servicio.ServicioSpa;
import SistemaGestionHotelera.strategy.DescuentoPorcentual;
import SistemaGestionHotelera.strategy.SinDescuento;

import java.time.LocalDate;

/**
 * Clase de ejecucion / demostracion funcional del sistema (Fase 2).
 * Recorre los casos de uso principales mostrando la colaboracion entre
 * los patrones aplicados: Facade, Singleton, Factory Method, Strategy,
 * State y Decorator.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(" SISTEMA DE GESTION HOTELERA - Grupo 13 (Fase 2)");
        System.out.println("====================================================\n");

        // La fachada inicializa toda la infraestructura (Facade + Singleton DB)
        SistemaHotelFacade hotel = new SistemaHotelFacade();

        // --- CU1: Registrar huespedes ---
        System.out.println("--- CU1: Registrar huespedes ---");
        hotel.registrarHuesped(1, "Ana Gomez", "30111222", "ana@mail.com", "1145678900");
        hotel.registrarHuesped(2, "Luis Perez", "28999111", "luis@mail.com", "1156789011");

        // --- CU2: Registrar habitaciones (Factory Method) ---
        System.out.println("\n--- CU2: Registrar habitaciones (Factory Method) ---");
        hotel.registrarHabitacion(TipoHabitacion.STANDARD, 101);
        hotel.registrarHabitacion(TipoHabitacion.PREMIUM, 201);
        hotel.registrarHabitacion(TipoHabitacion.SUITE, 301);

        // --- CU4: Consultar disponibilidad ---
        System.out.println("\n--- CU4: Consultar disponibilidad ---");
        for (Habitacion h : hotel.consultarDisponibilidad()) {
            System.out.println("  " + h);
        }

        // --- CU3: Crear reserva (Strategy de descuento) ---
        System.out.println("\n--- CU3: Crear reserva ---");
        Reserva reserva = hotel.realizarReserva(
                1, 201,
                LocalDate.of(2026, 7, 10),
                LocalDate.of(2026, 7, 13),
                new DescuentoPorcentual(15));   // promocion del 15%
        System.out.println("  " + reserva);

        // --- CU7: Agregar servicios adicionales (Decorator) ---
        System.out.println("\n--- CU7: Agregar servicios adicionales (Decorator) ---");
        ServicioReserva servicios = new ReservaBase(reserva.getCostoTotal());
        servicios = new ServicioDesayuno(servicios);
        servicios = new ServicioSpa(servicios);
        servicios = new ServicioCochera(servicios);
        reserva.agregarServicio(servicios);
        System.out.println("  Servicios: " + servicios.getDescripcion());

        // --- CU8: Calcular costo final ---
        System.out.println("\n--- CU8: Calcular costo final ---");
        System.out.println("  Costo total con servicios y descuento: $" + reserva.calcularCosto());

        // --- CU5: Confirmar reserva (State) ---
        System.out.println("\n--- CU5: Confirmar reserva (State) ---");
        hotel.confirmarReserva(reserva.getId());
        System.out.println("  Estado actual: " + reserva.getEstado().getNombre());

        // --- CU10: Registrar pago ---
        System.out.println("\n--- CU10: Registrar pago ---");
        hotel.registrarPago(reserva, "Tarjeta de credito");

        // --- Finalizar estadia (check-out) ---
        System.out.println("\n--- Check-out: Finalizar estadia (State) ---");
        hotel.finalizarReserva(reserva.getId());
        System.out.println("  Estado actual: " + reserva.getEstado().getNombre());

        // --- Demostracion de transicion invalida (State) ---
        System.out.println("\n--- Validacion de transicion invalida (State) ---");
        try {
            hotel.confirmarReserva(reserva.getId());
        } catch (IllegalStateException e) {
            System.out.println("  Excepcion controlada: " + e.getMessage());
        }

        // --- Segunda reserva sin descuento + cancelacion ---
        System.out.println("\n--- CU3 + CU6: Reserva y cancelacion ---");
        Reserva reserva2 = hotel.realizarReserva(
                2, 301,
                LocalDate.of(2026, 8, 1),
                LocalDate.of(2026, 8, 5),
                new SinDescuento());
        System.out.println("  " + reserva2);
        hotel.cancelarReserva(reserva2.getId());
        System.out.println("  Estado actual: " + reserva2.getEstado().getNombre());

        // --- CU11: Consultar historial ---
        System.out.println("\n--- CU11: Consultar historial de reservas ---");
        for (Reserva r : hotel.consultarHistorial()) {
            System.out.println("  " + r);
        }

        System.out.println("\n==============  FIN DE LA DEMOSTRACION  ==============");
    }
}
