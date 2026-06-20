package SistemaGestionHotelera.repository;

/**
 * Patron SINGLETON.
 * Garantiza una unica instancia de conexion/configuracion de acceso a la
 * base de datos durante toda la ejecucion del sistema.
 */
public class DatabaseConnection {

    private static DatabaseConnection instancia;

    private final String urlConexion;
    private boolean conectado;

    private DatabaseConnection() {
        this.urlConexion = "jdbc:mysql://localhost:3306/sistema_hotel";
        this.conectado = false;
    }

    /** Punto de acceso global a la unica instancia. */
    public static DatabaseConnection getInstancia() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }

    public void conectar() {
        if (!conectado) {
            conectado = true;
            System.out.println("[DB] Conexion establecida con " + urlConexion);
        }
    }

    public boolean isConectado() {
        return conectado;
    }
}
