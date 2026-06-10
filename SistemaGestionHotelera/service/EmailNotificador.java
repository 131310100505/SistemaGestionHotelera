package SistemaGestionHotelera.service;

/** Notificador concreto que envia avisos por correo electronico. */
public class EmailNotificador implements Notificador {

    @Override
    public void enviar(String mensaje, String destinatario) {
        System.out.println("[EMAIL -> " + destinatario + "] " + mensaje);
    }
}
