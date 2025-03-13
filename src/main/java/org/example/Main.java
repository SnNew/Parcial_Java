package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorCabinas gestor = new GestorCabinas();
        boolean salir = false;

        System.out.println("SISTEMA DE CONTROL DE GASTOS TELEFÓNICOS");

        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Crear cabina telefónica");
            System.out.println("2. Iniciar llamada");
            System.out.println("3. Colgar llamada");
            System.out.println("4. Mostrar información detallada por cabina");
            System.out.println("5. Mostrar consolidado total");
            System.out.println("6. Reiniciar cabina telefónica");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    Cabina nuevaCabina = gestor.crearCabina();
                    System.out.println("Cabina telefónica #" + nuevaCabina.getId() + " creada exitosamente.");
                }
                case 2 -> {
                    if (gestor.obtenerNumeroCabinas() == 0) {
                        System.out.println("No hay cabinas telefónicas registradas. Cree una primero.");
                        continue;
                    }

                    System.out.println("Cabinas disponibles:");
                    Cabina[] cabinas = gestor.obtenerCabinas();
                    for (int i = 0; i < gestor.obtenerNumeroCabinas(); i++) {
                        System.out.println("Cabina #" + cabinas[i].getId());
                    }

                    System.out.print("Ingrese el número de cabina: ");
                    int idCabina = scanner.nextInt();
                    scanner.nextLine();

                    Cabina cabina = gestor.obtenerCabina(idCabina);
                    if (cabina == null) {
                        System.out.println("Cabina no encontrada.");
                        continue;
                    }

                    if (cabina.tieneLlamadaEnCurso()) {
                        System.out.println("La cabina ya tiene una llamada en curso. Debe colgarla primero.");
                        continue;
                    }

                    System.out.println("\nSeleccione el tipo de llamada:");
                    System.out.println("1. Local ($" + cabina.COSTO_LOCAL + "/min)");
                    System.out.println("2. Larga Distancia ($" + cabina.COSTO_LARGA_DISTANCIA + "/min)");
                    System.out.println("3. Celular ($" + cabina.COSTO_CELULAR + "/min)");
                    System.out.print("Opción: ");
                    int tipoLlamada = scanner.nextInt();
                    scanner.nextLine();

                    String tipo;
                    switch (tipoLlamada) {
                        case 1 -> tipo = "Local";
                        case 2 -> tipo = "Larga Distancia";
                        case 3 -> tipo = "Celular";
                        default -> {
                            System.out.println("Tipo de llamada no válido.");
                            continue;
                        }
                    }

                    cabina.iniciarLlamada(tipo);
                    System.out.println("Llamada " + tipo + " iniciada en la cabina #" + cabina.getId());
                }
                case 3 -> {
                    if (gestor.obtenerNumeroCabinas() == 0) {
                        System.out.println("No hay cabinas telefónicas registradas.");
                        continue;
                    }

                    System.out.println("Cabinas disponibles:");
                    Cabina[] cabinas = gestor.obtenerCabinas();
                    for (int i = 0; i < gestor.obtenerNumeroCabinas(); i++) {
                        System.out.println("Cabina #" + cabinas[i].getId() +
                                (cabinas[i].tieneLlamadaEnCurso() ? " (Llamada en curso)" : ""));
                    }

                    System.out.print("Ingrese el número de cabina: ");
                    int idCabina = scanner.nextInt();
                    scanner.nextLine();

                    Cabina cabina = gestor.obtenerCabina(idCabina);
                    if (cabina == null) {
                        System.out.println("Cabina no encontrada.");
                        continue;
                    }

                    if (!cabina.tieneLlamadaEnCurso()) {
                        System.out.println("La cabina no tiene una llamada en curso.");
                        continue;
                    }

                    int duracion = gestor.generarDuracionAleatoria();

                    cabina.colgarLlamada(duracion);
                    System.out.println("Llamada finalizada con duración de " + duracion + " minutos.");
                }
                case 4 -> {
                    if (gestor.obtenerNumeroCabinas() == 0) {
                        System.out.println("No hay cabinas telefónicas registradas.");
                        continue;
                    }

                    System.out.println("Cabinas disponibles:");
                    Cabina[] cabinas = gestor.obtenerCabinas();
                    for (int i = 0; i < gestor.obtenerNumeroCabinas(); i++) {
                        System.out.println("Cabina #" + cabinas[i].getId() +
                                (cabinas[i].tieneLlamadaEnCurso() ? " (Llamada en curso)" : ""));
                    }

                    System.out.print("Ingrese el número de cabina para ver su información: ");
                    int idCabina = scanner.nextInt();
                    scanner.nextLine();

                    Cabina cabina = gestor.obtenerCabina(idCabina);
                    if (cabina == null) {
                        System.out.println("Cabina no encontrada.");
                        continue;
                    }

                    System.out.println("\nINFORMACIÓN DETALLADA");
                    System.out.println(cabina.obtenerInformacionDetallada());
                }
                case 5 -> {
                    if (gestor.obtenerNumeroCabinas() == 0) {
                        System.out.println("No hay cabinas telefónicas registradas.");
                        continue;
                    }

                    System.out.println("\nCONSOLIDADO TOTAL");
                    System.out.println(gestor.obtenerConsolidadoTotal());
                }
                case 6 -> {
                    if (gestor.obtenerNumeroCabinas() == 0) {
                        System.out.println("No hay cabinas telefónicas registradas.");
                        continue;
                    }

                    System.out.println("Cabinas disponibles:");
                    Cabina[] cabinas = gestor.obtenerCabinas();
                    for (int i = 0; i < gestor.obtenerNumeroCabinas(); i++) {
                        System.out.println("Cabina #" + cabinas[i].getId());
                    }

                    System.out.print("Ingrese el número de cabina para reiniciar: ");
                    int idCabina = scanner.nextInt();
                    scanner.nextLine();

                    Cabina cabina = gestor.obtenerCabina(idCabina);
                    if (cabina == null) {
                        System.out.println("Cabina no encontrada.");
                        continue;
                    }

                    cabina.reiniciar();
                    System.out.println("Cabina #" + cabina.getId() + " reiniciada con éxito.");
                }
                case 7 -> {
                    salir = true;
                    System.out.println("Gracias por usar el sistema de control de gastos telefónicos.");
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}