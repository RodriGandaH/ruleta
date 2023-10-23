import java.util.*;

public class Main {
        private static Scanner scanner = new Scanner(System.in);
        private static int rondasJugadas = 0;
        private static int rondasGanadas = 0;
        private static int rondasPerdidas = 0;
        private static int gananciaMasAlta = 0;
        private static String nombreJugador;
    
    public static void main(String[] args) {
             System.out.println("Bienvenido al juego de la Ruleta!");
            System.out.print("Por favor, introduce tu nombre: ");
            String nombre = scanner.nextLine();
            nombreJugador = nombre;
            Jugador jugador = new Jugador(nombre, solicitarSaldoInicial());
            Mesa mesa = new Mesa();
            Ruleta ruleta = new Ruleta();
            boolean continuarJuego = true;
            while (continuarJuego) {
                if (jugador.getSaldo() <= 0) {
                    System.out.println("Tu saldo es $0. Debes agregar más dinero para continuar jugando.");
                    agregarSaldo(jugador);
                }
                int saldoAnterior = jugador.getSaldo();
                realizarApuesta(jugador, mesa);
                mesa.resolverApuestas(ruleta);
                actualizarEstadisticas(jugador, saldoAnterior);
                System.out.println("Tu saldo actual es: $" + jugador.getSaldo());
                mostrarEstadisticas();
                if (preguntarSiContinuar()) {
                    agregarSaldo(jugador);
                } else {
                    continuarJuego = false;
                    System.out.println("Has salido del juego. ¡Gracias por jugar!");
                }
            }
        }
    
    private static int solicitarSaldoInicial() {
            int saldoInicial = -1;
            do {
                System.out.print("Hola, "+nombreJugador+"! ¿Cuanto dinero te gustaria usar para jugar? ");
                try {
                    saldoInicial = Integer.parseInt(scanner.nextLine());
                    if (saldoInicial < 0) {
                        System.out.println("El saldo inicial debe ser un número entero positivo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un número entero válido.");
                }
            } while (saldoInicial < 0);
            return saldoInicial;
    }
    
    private static void realizarApuesta(Jugador jugador, Mesa mesa) {
        
        Apuesta apuestaColor = new ApuestaColor(0, jugador, "rojo"); 
        Apuesta apuestaNumero = new ApuestaNumero(0, jugador, 0); 
        
        int cantidad = -1;
        do {
            System.out.print("¿Cuanto te gustaria apostar? ");
            try {
                cantidad = Integer.parseInt(scanner.nextLine());
                if (cantidad > jugador.getSaldo()) {
                    System.out.println("No puedes apostar esa cantidad porque tu saldo es $" + jugador.getSaldo());
                } else if (cantidad < 0) {
                    System.out.println("La cantidad apostada debe ser un número entero positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número entero válido.");
            }
        } while (cantidad > jugador.getSaldo() || cantidad < 0);
    
        int opcion = -1;
        do {
            System.out.println("¿A que te gustaria apostar?");
            System.out.println("1. Numero");
            System.out.println("2. Color");
            System.out.print("Por favor, selecciona una opcion: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion != 1 && opcion != 2) {
                    System.out.println("Por favor, selecciona una opción válida (1 o 2).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            }
        } while (opcion != 1 && opcion != 2);
    
        if (opcion == 1) {
            int numero = -1;
            do {
                System.out.print("Por favor, introduce el numero al que te gustaria apostar (0-36): ");
                try {
                    numero = Integer.parseInt(scanner.nextLine());
                    if (numero < 0 || numero > 36) {
                        System.out.println("Por favor, ingresa un número válido entre 0 y 36.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un número válido.");
                }
            } while (numero < 0 || numero > 36);
            
            apuestaNumero = new ApuestaNumero(cantidad, jugador, numero);
            mesa.agregarApuesta(jugador, apuestaNumero);
        } else if (opcion == 2) {
            String color = "";
            do {
                System.out.println("¿Que color eliges?");
                System.out.println("1. Rojo");
                System.out.println("2. Verde");
                System.out.print("Por favor, selecciona una opcion: ");
                String colorOpcion = scanner.nextLine().toLowerCase();
                if (colorOpcion.equals("1") || colorOpcion.equals("rojo") || colorOpcion.equals("r")) {
                    color = "rojo";
                } else if (colorOpcion.equals("2") || colorOpcion.equals("verde") || colorOpcion.equals("v")) {
                    color = "verde";
                } else {
                    System.out.println("Por favor, selecciona una opción válida ('1', 'rojo', 'r', '2', 'verde', 'v').");
                }
            } while (!color.equals("rojo") && !color.equals("verde"));
            
            apuestaColor = new ApuestaColor(cantidad, jugador, color);
            mesa.agregarApuesta(jugador, apuestaColor);
        }
    }


    
    private static boolean preguntarSiContinuar() {
            String respuesta;
            do {
                System.out.print("¿Te gustaria jugar otra ronda? (si/no): ");
                respuesta = scanner.nextLine().toLowerCase();
                if (!respuesta.equals("si") && !respuesta.equals("no") && !respuesta.equals("s") && !respuesta.equals("n")) {
                    System.out.println("Por favor, responde con 'si', 'no', 's' o 'n'.");
                }
            } while (!respuesta.equals("si") && !respuesta.equals("no") && !respuesta.equals("s") && !respuesta.equals("n"));
            return respuesta.equals("si") || respuesta.equals("s");
        }
    
    private static void agregarSaldo(Jugador jugador) {
            String respuesta;
            do {
                System.out.print("¿Te gustaria agregar mas dinero a tu saldo? (si/no): ");
                respuesta = scanner.nextLine().toLowerCase();
                if (!respuesta.equals("si") && !respuesta.equals("no") && !respuesta.equals("s") && !respuesta.equals("n")) {
                    System.out.println("Por favor, responde con 'si', 'no', 's' o 'n'.");
                }
            } while (!respuesta.equals("si") && !respuesta.equals("no") && !respuesta.equals("s") && !respuesta.equals("n"));
    
            if (respuesta.equals("si") || respuesta.equals("s")) {
                int cantidad = -1;
                do {
                    System.out.print("Por favor, introduce la cantidad que te gustaria agregar: ");
                    try {
                        cantidad = Integer.parseInt(scanner.nextLine());
                        if (cantidad < 0) {
                            System.out.println("La cantidad debe ser un número entero positivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingresa un número entero válido.");
                    }
                } while (cantidad < 0);
                
                jugador.agregarSaldo(cantidad);
                System.out.println("Tu nuevo saldo es: $" + jugador.getSaldo());
            }
        }
    
    
    private static void mostrarEstadisticas() {
            System.out.println("\nEstadisticas del juego:");
            System.out.println("- Rondas jugadas: " + rondasJugadas);
            System.out.println("- Rondas ganadas: " + rondasGanadas);
            System.out.println("- Rondas perdidas: " + rondasPerdidas);
            System.out.println("- Ganancia mas alta en una ronda: $" + gananciaMasAlta);
        }
        
    private static void actualizarEstadisticas(Jugador jugador, int saldoAnterior) {
            rondasJugadas++;
            if (jugador.getSaldo() > saldoAnterior) {
                rondasGanadas++;
                int ganancia = jugador.getSaldo() - saldoAnterior;
                if (ganancia > gananciaMasAlta) {
                    gananciaMasAlta = ganancia;
                }
            } else if (jugador.getSaldo() < saldoAnterior) {
                rondasPerdidas++;
            }
        }
}