package SistemaGestionHotelera.model.servicio;

/** Patron DECORATOR - Decorador concreto: servicio de desayuno. */
public class ServicioDesayuno extends ServicioDecorator {

    private static final double PRECIO = 4500.0;

    public ServicioDesayuno(ServicioReserva servicio) {
        super(servicio);
    }

    @Override
    public String getDescripcion() {
        return servicio.getDescripcion() + " + Desayuno";
    }

    @Override
    public double getCosto() {
        return servicio.getCosto() + PRECIO;
    }
}
