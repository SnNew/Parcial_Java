package org.example;

public class Cabina {

    private Llamada[] llamadas;
    private int id;
    private int cantidadLlamadas;
    private int capacidadMaxima;
    private String tipoLlamadaActual;
    private boolean llamadaActiva;

    public final double COSTO_LOCAL = 50.0;
    public final double COSTO_LARGA_DISTANCIA = 350.0;
    public final double COSTO_CELULAR = 150.0;

    public Cabina(Llamada[] llamadas, int id, int cantidadLlamadas, int capacidadMaxima, String tipoLlamadaActual, boolean llamadaActiva) {
        this.llamadas = llamadas;
        this.id = id;
        this.cantidadLlamadas = cantidadLlamadas;
        this.capacidadMaxima = capacidadMaxima;
        this.tipoLlamadaActual = tipoLlamadaActual;
        this.llamadaActiva = llamadaActiva;
    }

    public int getId() {
        return id;
    }

    public boolean tieneLlamadaEnCurso() {
        return llamadaActiva;
    }

    public void iniciarLlamada(String tipo) {
        this.tipoLlamadaActual = tipo;
        this.llamadaActiva = true;
    }

    public void colgarLlamada(int duracion) {
        if (!llamadaActiva) {
            return;
        }

        double costo = 0;

        switch (tipoLlamadaActual) {
            case "Local" -> costo = duracion * COSTO_LOCAL;
            case "Larga Distancia" -> costo = duracion * COSTO_LARGA_DISTANCIA;
            case "Celular" -> costo = duracion * COSTO_CELULAR;
        }

        //Esto lo investigue por aparte, es para crear un nuevo array por si el actual se llena
        if (cantidadLlamadas >= capacidadMaxima) {
            capacidadMaxima *= 2;
            Llamada[] nuevoArreglo = new Llamada[capacidadMaxima];
            System.arraycopy(llamadas, 0, nuevoArreglo, 0, cantidadLlamadas);
            llamadas = nuevoArreglo;
        }

        Llamada nuevaLlamada = new Llamada(tipoLlamadaActual, duracion, costo);
        llamadas[cantidadLlamadas++] = nuevaLlamada;

        this.llamadaActiva = false;
        this.tipoLlamadaActual = null;
    }

    public int obtenerNumeroLlamadas() {
        return cantidadLlamadas;
    }

    public int obtenerDuracionTotal() {
        int duracionTotal = 0;
        for (int i = 0; i < cantidadLlamadas; i++) {
            duracionTotal += llamadas[i].getDuracionMinutos();
        }
        return duracionTotal;
    }

    public double obtenerCostoTotal() {
        double costoTotal = 0;
        for (int i = 0; i < cantidadLlamadas; i++) {
            costoTotal += llamadas[i].getCosto();
        }
        return costoTotal;
    }

    public void reiniciar() {
        cantidadLlamadas = 0;
        llamadaActiva = false;
        tipoLlamadaActual = null;
    }

    public String obtenerInformacionDetallada() {
        return "Cabina número" + id +
                "\nNúmero de llamadas: " + obtenerNumeroLlamadas() +
                "\nDuración total: " + obtenerDuracionTotal() + " minutos" +
                "\nCosto total: $" + obtenerCostoTotal() + " pesos";
    }
}
