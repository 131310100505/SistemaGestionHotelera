package SistemaGestionHotelera.service;

/** Notificador concreto que envia avisos mediante notificacion push de la app. */
public class AppNotificador implements Notificador {

    @Override
    public void enviar(String mensaje, String destinatario) {
        System.out.println("[APP PUSH -> " + destinatario + "] " + mensaje);
    }
}
