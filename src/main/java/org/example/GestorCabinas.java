package org.example;
import java.util.Random;

public class GestorCabinas {
    private Cabina[] cabinas;
    private int cantidadCabinas;
    private int capacidadMaxima;
    private Random random = new Random();

    public GestorCabinas() {
        this.capacidadMaxima = 10;
        this.cabinas = new Cabina[capacidadMaxima];
        this.cantidadCabinas = 0;
    }

    public Cabina crearCabina() {
        if (cantidadCabinas >= capacidadMaxima) {
            capacidadMaxima *= 2;
            Cabina[] nuevoArreglo = new Cabina[capacidadMaxima];
        }

        int nuevoId = cantidadCabinas + 1;
        int nuevaCapacidad = 100;

        Llamada[] nuevasLlamadas = new Llamada[nuevaCapacidad];

        Cabina nuevaCabina = new Cabina(nuevasLlamadas, nuevoId, 0, nuevaCapacidad, null, false);
        cabinas[cantidadCabinas++] = nuevaCabina;
        return nuevaCabina;
    }

    public Cabina obtenerCabina(int id) {
        for (int i = 0; i < cantidadCabinas; i++) {
            if (cabinas[i].getId() == id) {
                return cabinas[i];
            }
        }
        return null;
    }

    public int obtenerNumeroCabinas() {
        return cantidadCabinas;
    }

    public int obtenerNumeroTotalLlamadas() {
        int totalLlamadas = 0;
        for (int i = 0; i < cantidadCabinas; i++) {
            totalLlamadas += cabinas[i].obtenerNumeroLlamadas();
        }
        return totalLlamadas;
    }

    public int obtenerDuracionTotalLlamadas() {
        int duracionTotal = 0;
        for (int i = 0; i < cantidadCabinas; i++) {
            duracionTotal += cabinas[i].obtenerDuracionTotal();
        }
        return duracionTotal;
    }

    public double obtenerCostoTotalLlamadas() {
        double costoTotal = 0;
        for (int i = 0; i < cantidadCabinas; i++) {
            costoTotal += cabinas[i].obtenerCostoTotal();
        }
        return costoTotal;
    }

    public String obtenerConsolidadoTotal() {
        return "CONSOLIDADO TOTAL DE CABINAS" +
                "\nNúmero total de cabinas: " + obtenerNumeroCabinas() +
                "\nNúmero total de llamadas: " + obtenerNumeroTotalLlamadas() +
                "\nDuración total de llamadas: " + obtenerDuracionTotalLlamadas() + " minutos" +
                "\nCosto total: $" + obtenerCostoTotalLlamadas() + " pesos";
    }

    public Cabina[] obtenerCabinas() {
        return cabinas;
    }

    public int generarDuracionAleatoria() {
        return random.nextInt(60) + 1;
    }
}
