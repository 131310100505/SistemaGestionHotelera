package com.grupo13.hotel.model;

/**
 * Clase de dominio que representa una habitacion del hotel.
 * GRASP - Experto en Informacion: conoce su precio y disponibilidad.
 */
public class Habitacion {

    private int numero;
    private TipoHabitacion tipo;
    private double precioBase;
    private boolean disponible;

    public Habitacion(int numero, TipoHabitacion tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    /** Precio efectivo por noche segun el tipo de habitacion. */
    public double getPrecioPorNoche() {
        return precioBase * tipo.getMultiplicador();
    }

    public boolean estaDisponible() { return disponible; }
    public void ocupar() { this.disponible = false; }
    public void liberar() { this.disponible = true; }

    public int getNumero() { return numero; }
    public TipoHabitacion getTipo() { return tipo; }
    public double getPrecioBase() { return precioBase; }

    @Override
    public String toString() {
        return "Habitacion " + numero + " [" + tipo + "] $" + getPrecioPorNoche()
                + "/noche - " + (disponible ? "DISPONIBLE" : "OCUPADA");
    }
}
