package SistemaGestionHotelera.service;

/**
 * Interfaz de notificacion.
 * SOLID - DIP / ISP: los controladores dependen de esta abstraccion y no
 * de un mecanismo concreto (email, app, etc.), permitiendo intercambiarlos.
 */
public interface Notificador {

    void enviar(String mensaje, String destinatario);
}
