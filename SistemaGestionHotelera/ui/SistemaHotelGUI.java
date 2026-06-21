package SistemaGestionHotelera.ui;

import SistemaGestionHotelera.facade.SistemaHotelFacade;
import SistemaGestionHotelera.model.Habitacion;
import SistemaGestionHotelera.model.Pago;
import SistemaGestionHotelera.model.Reserva;
import SistemaGestionHotelera.model.TipoHabitacion;
import SistemaGestionHotelera.strategy.DescuentoPorcentual;
import SistemaGestionHotelera.strategy.DescuentoStrategy;
import SistemaGestionHotelera.strategy.DescuentoTemporada;
import SistemaGestionHotelera.strategy.SinDescuento;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class SistemaHotelGUI extends JFrame {

    private static final SistemaHotelFacade hotel = new SistemaHotelFacade();
    private final JTextArea salida = new JTextArea();
    private final NumberFormat moneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
    private final boolean modoHuesped;

    public SistemaHotelGUI(boolean modoHuesped) {
        this.modoHuesped = modoHuesped;

        setTitle(modoHuesped ? "Sistema de Gestión Hotelera - Huésped" : "Sistema de Gestión Hotelera - Administrador");
        setSize(900, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        JLabel titulo = new JLabel(
                modoHuesped ? "Sistema de Gestión Hotelera - Huésped" : "Sistema de Gestión Hotelera - Administrador",
                SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 8, 8));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 10));

        if (modoHuesped) {
            agregarBoton(panelBotones, "Registrarme como huésped", this::registrarHuesped);
            agregarBoton(panelBotones, "Consultar disponibilidad", this::consultarDisponibilidad);
            agregarBoton(panelBotones, "Crear reserva", this::crearReserva);
            agregarBoton(panelBotones, "Consultar estado de reserva", this::consultarEstadoReserva);
            agregarBoton(panelBotones, "Consultar historial", this::consultarHistorial);
            agregarBoton(panelBotones, "Limpiar pantalla", () -> salida.setText(""));
            agregarBoton(panelBotones,
                    "Volver al menú principal",
                    this::volverMenuPrincipal);
        } else {
            agregarBoton(panelBotones, "Registrar huésped", this::registrarHuesped);
            agregarBoton(panelBotones, "Registrar habitación", this::registrarHabitacion);
            agregarBoton(panelBotones, "Consultar disponibilidad", this::consultarDisponibilidad);
            agregarBoton(panelBotones, "Crear reserva", this::crearReserva);
            agregarBoton(panelBotones, "Consultar estado de reserva", this::consultarEstadoReserva);
            agregarBoton(panelBotones, "Confirmar reserva", this::confirmarReserva);
            agregarBoton(panelBotones, "Cancelar reserva", this::cancelarReserva);
            agregarBoton(panelBotones, "Finalizar reserva", this::finalizarReserva);
            agregarBoton(panelBotones, "Agregar servicio", this::agregarServicio);
            agregarBoton(panelBotones, "Registrar pago", this::registrarPago);
            agregarBoton(panelBotones, "Consultar historial", this::consultarHistorial);
            agregarBoton(panelBotones, "Limpiar pantalla", () -> salida.setText(""));
            agregarBoton(panelBotones,
                    "Volver al menú principal",
                    this::volverMenuPrincipal);
        }

        salida.setEditable(false);
        salida.setFont(new Font("Consolas", Font.PLAIN, 14));
        salida.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(salida);
        scroll.setBorder(BorderFactory.createTitledBorder("Salida del sistema"));

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);

        if (modoHuesped) {
            cargarHabitacionesInicialesParaHuesped();
        }

        mensajeInicial();
    }

    private void cargarHabitacionesInicialesParaHuesped() {
        try {
            hotel.registrarHabitacion(TipoHabitacion.STANDARD, 101);
            hotel.registrarHabitacion(TipoHabitacion.PREMIUM, 201);
            hotel.registrarHabitacion(TipoHabitacion.SUITE, 301);
        } catch (Exception ignored) {
        }
    }

    private void agregarBoton(JPanel panel, String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.addActionListener(e -> ejecutarSeguro(accion));
        panel.add(boton);
    }

    private void ejecutarSeguro(Runnable accion) {
        try {
            accion.run();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarHuesped() {
        int id = pedirEntero("ID del huésped:");
        String nombre = pedirTexto("Nombre:");
        String dni = pedirTexto("DNI:");
        String email = pedirTexto("Email:");
        String telefono = pedirTexto("Teléfono:");

        hotel.registrarHuesped(id, nombre, dni, email, telefono);
        escribir("Huésped registrado correctamente: " + nombre);
    }

    private void registrarHabitacion() {
        int numero = pedirEntero("Número de habitación:");

        TipoHabitacion tipo = (TipoHabitacion) JOptionPane.showInputDialog(
                this,
                "Tipo de habitación:",
                "Registrar habitación",
                JOptionPane.QUESTION_MESSAGE,
                null,
                TipoHabitacion.values(),
                TipoHabitacion.STANDARD
        );

        if (tipo == null) return;

        Habitacion habitacion = hotel.registrarHabitacion(tipo, numero);
        escribir("Habitación registrada correctamente:");
        escribir(habitacion.toString());
    }

    private void consultarDisponibilidad() {
        escribir("\n--- Habitaciones disponibles ---");

        if (hotel.consultarDisponibilidad().isEmpty()) {
            escribir("No hay habitaciones disponibles.");
            return;
        }

        for (Habitacion h : hotel.consultarDisponibilidad()) {
            escribir(h.toString());
        }
    }

    private void crearReserva() {
        int idHuesped = pedirEntero("ID del huésped:");
        int numeroHabitacion = pedirEntero("Número de habitación:");
        LocalDate ingreso = pedirFecha("Fecha de ingreso (AAAA-MM-DD):");
        LocalDate salidaFecha = pedirFecha("Fecha de salida (AAAA-MM-DD):");

        if (!salidaFecha.isAfter(ingreso)) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de ingreso.");
        }

        DescuentoStrategy descuento = elegirDescuento();

        Reserva reserva = hotel.realizarReserva(
                idHuesped,
                numeroHabitacion,
                ingreso,
                salidaFecha,
                descuento
        );

        escribir("\nReserva creada correctamente:");
        escribir(reserva.toString());
        escribir("Servicios: " + reserva.getServicios().getDescripcion());
        escribir("Total: " + moneda.format(reserva.calcularCosto()));
    }

    private void consultarEstadoReserva() {
        int idReserva = pedirEntero("ID de reserva:");

        Reserva reserva = hotel.buscarReserva(idReserva);

        escribir("\n--- Estado de reserva ---");
        escribir("Reserva #" + reserva.getId());
        escribir("Huésped: " + reserva.getHuesped().getNombre());
        escribir("Estado actual: " + reserva.getEstado().getNombre());
        escribir("Total: " + moneda.format(reserva.calcularCosto()));
    }

    private DescuentoStrategy elegirDescuento() {
        String[] opciones = {"Sin descuento", "10%", "Temporada baja"};

        String opcion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione descuento:",
                "Descuento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == null || opcion.equals("Sin descuento")) {
            return new SinDescuento();
        }

        if (opcion.equals("10%")) {
            return new DescuentoPorcentual(10);
        }

        return new DescuentoTemporada();
    }

    private void confirmarReserva() {
        int idReserva = pedirEntero("ID de reserva:");
        hotel.confirmarReserva(idReserva);
        escribir("Reserva confirmada correctamente: #" + idReserva);
    }

    private void cancelarReserva() {
        int idReserva = pedirEntero("ID de reserva:");
        hotel.cancelarReserva(idReserva);
        escribir("Reserva cancelada correctamente: #" + idReserva);
    }

    private void finalizarReserva() {
        int idReserva = pedirEntero("ID de reserva:");
        hotel.finalizarReserva(idReserva);
        escribir("Reserva finalizada correctamente: #" + idReserva);
    }

    private void agregarServicio() {
        int idReserva = pedirEntero("ID de reserva:");

        String[] servicios = {"Desayuno", "Spa", "Cochera"};

        String servicio = (String) JOptionPane.showInputDialog(
                this,
                "Servicio adicional:",
                "Agregar servicio",
                JOptionPane.QUESTION_MESSAGE,
                null,
                servicios,
                servicios[0]
        );

        if (servicio == null) return;

        hotel.agregarServicioAdicional(idReserva, servicio);

        Reserva reserva = hotel.buscarReserva(idReserva);

        escribir("Servicio agregado: " + servicio);
        escribir("Servicios actuales: " + reserva.getServicios().getDescripcion());
        escribir("Nuevo total: " + moneda.format(reserva.calcularCosto()));
    }

    private void registrarPago() {
        int idReserva = pedirEntero("ID de reserva:");

        String[] metodos = {"Efectivo", "Tarjeta", "Transferencia"};

        String metodo = (String) JOptionPane.showInputDialog(
                this,
                "Método de pago:",
                "Registrar pago",
                JOptionPane.QUESTION_MESSAGE,
                null,
                metodos,
                metodos[0]
        );

        if (metodo == null) return;

        Pago pago = hotel.registrarPago(idReserva, metodo);

        escribir("Pago registrado correctamente:");
        escribir(pago.toString());
    }

    private void consultarHistorial() {
        escribir("\n--- Historial de reservas ---");

        if (hotel.consultarHistorial().isEmpty()) {
            escribir("Todavía no hay reservas registradas.");
            return;
        }

        for (Reserva r : hotel.consultarHistorial()) {
            escribir(r.toString());
            escribir("Servicios: " + r.getServicios().getDescripcion());
            escribir("Total actualizado: " + moneda.format(r.calcularCosto()));
            escribir("--------------------------------------");
        }
    }

    private int pedirEntero(String mensaje) {
        String valor = pedirTexto(mensaje);

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ingresar un número válido.");
        }
    }

    private String pedirTexto(String mensaje) {
        String valor = JOptionPane.showInputDialog(this, mensaje);

        if (valor == null) {
            throw new IllegalArgumentException("Operación cancelada.");
        }

        valor = valor.trim();

        if (valor.isEmpty()) {
            throw new IllegalArgumentException("El campo no puede estar vacío.");
        }

        return valor;
    }

    private LocalDate pedirFecha(String mensaje) {
        String valor = pedirTexto(mensaje);

        try {
            return LocalDate.parse(valor);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha debe tener formato AAAA-MM-DD. Ejemplo: 2026-06-20");
        }
    }

    private void escribir(String texto) {
        salida.append(texto + "\n");
    }

    private void mensajeInicial() {
        escribir("Bienvenido al Sistema de Gestión Hotelera.");

        if (modoHuesped) {
            escribir("Modo de ingreso: HUÉSPED");
            escribir("Opciones disponibles:");
            escribir("1) Registrarme como huésped");
            escribir("2) Consultar disponibilidad");
            escribir("3) Crear reserva");
            escribir("4) Consultar estado de reserva");
            escribir("5) Consultar historial");
            escribir("");
        } else {
            escribir("Modo de ingreso: ADMINISTRADOR");
            escribir("Use los botones de la izquierda para operar el sistema.");
            escribir("Recomendación de prueba:");
            escribir("1) Registrar huésped");
            escribir("2) Registrar habitación");
            escribir("3) Crear reserva");
            escribir("4) Confirmar / agregar servicio / registrar pago");
            escribir("");
        }
    }

    private static void mostrarMenuInicial() {
        JFrame inicio = new JFrame("Sistema de Gestión Hotelera");
        inicio.setSize(430, 280);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setLocationRelativeTo(null);
        inicio.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Sistema de Gestión Hotelera", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 55, 40, 55));

        JButton btnAdmin = new JButton("Ingresar como Administrador");
        JButton btnHuesped = new JButton("Ingresar como Huésped");

        btnAdmin.setFont(new Font("Arial", Font.BOLD, 14));
        btnHuesped.setFont(new Font("Arial", Font.BOLD, 14));

        btnAdmin.addActionListener(e -> {
            String clave = JOptionPane.showInputDialog(inicio, "Ingrese contraseña de administrador:");

            if (clave == null) return;

            if (!clave.equals("admin123")) {
                JOptionPane.showMessageDialog(inicio, "Contraseña incorrecta.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                return;
            }

            inicio.dispose();
            new SistemaHotelGUI(false).setVisible(true);
        });

        btnHuesped.addActionListener(e -> {
            inicio.dispose();
            new SistemaHotelGUI(true).setVisible(true);
        });

        panel.add(btnAdmin);
        panel.add(btnHuesped);

        inicio.add(titulo, BorderLayout.NORTH);
        inicio.add(panel, BorderLayout.CENTER);
        inicio.setVisible(true);
    }
    private void volverMenuPrincipal() {

        dispose();

        SwingUtilities.invokeLater(
                SistemaHotelGUI::mostrarMenuInicial
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaHotelGUI::mostrarMenuInicial);
    }
}