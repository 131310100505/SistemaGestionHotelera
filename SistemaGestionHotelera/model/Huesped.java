package com.grupo13.hotel.model;

/**
 * Clase de dominio que representa a un huesped del hotel.
 * GRASP - Experto en Informacion: concentra los datos del huesped.
 */
public class Huesped {

    private int id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;

    public Huesped(int id, String nombre, String dni, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    public String getDatos() {
        return "Huesped #" + id + " - " + nombre + " (DNI " + dni + ", " + email + ")";
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}
