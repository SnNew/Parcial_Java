package org.example;

public class Llamada {
    private String tipoLlamada;
    private int duracionMinutos;
    private double costo;

    public Llamada(String tipoLlamada, int duracionMinutos, double costo) {
        this.tipoLlamada = tipoLlamada;
        this.duracionMinutos = duracionMinutos;
        this.costo = costo;
    }

    public String getTipoLlamada() {
        return tipoLlamada;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public double getCosto() {
        return costo;
    }
}
