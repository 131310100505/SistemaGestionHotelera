package com.grupo13.hotel.model;

import java.time.LocalDate;

/**
 * Clase de dominio que representa el pago asociado a una reserva.
 */
public class Pago {

    private int id;
    private double monto;
    private LocalDate fechaPago;
    private String metodoPago;

    public Pago(int id, double monto, String metodoPago) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = LocalDate.now();
    }

    public int getId() { return id; }
    public double getMonto() { return monto; }
    public LocalDate getFechaPago() { return fechaPago; }
    public String getMetodoPago() { return metodoPago; }

    @Override
    public String toString() {
        return "Pago #" + id + " $" + monto + " (" + metodoPago + ") - " + fechaPago;
    }
}
