/** Notificador concreto que envia avisos por correo electronico. */
package SistemaGestionHotelera.service;

public class EmailNotificador implements Notificador {

    @Override
    public void enviar(String mensaje, String destinatario) {
        System.out.println("[EMAIL -> " + destinatario + "] " + mensaje);
    }
}
