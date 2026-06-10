package SistemaGestionHotelera.model.servicio;

/**
 * Patron DECORATOR - Decorador abstracto.
 * Mantiene una referencia al componente decorado y delega en el.
 * Las subclases agregan responsabilidades (servicios) de forma dinamica.
 */
public abstract class ServicioDecorator implements ServicioReserva {

    protected final ServicioReserva servicio;

    protected ServicioDecorator(ServicioReserva servicio) {
        this.servicio = servicio;
    }

    @Override
    public String getDescripcion() {
        return servicio.getDescripcion();
    }

    @Override
    public double getCosto() {
        return servicio.getCosto();
    }
}
