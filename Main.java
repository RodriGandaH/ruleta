import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int rondasJugadas = 0;
    private static int rondasGanadas = 0;
    private static int rondasPerdidas = 0;
    private static int gananciaMasAlta = 0;

    public static void main(String[] args) {
         System.out.println("Bienvenido al juego de la Ruleta!");
        System.out.print("Por favor, introduce tu nombre: ");
        String nombre = scanner.nextLine();
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
        System.out.print("¿Cuanto dinero te gustaria usar para jugar? ");
        return Integer.parseInt(scanner.nextLine());
    }
    private static void realizarApuesta(Jugador jugador, Mesa mesa) {
        int cantidad;
        do {
            System.out.print("¿Cuanto te gustaria apostar? ");
            cantidad = Integer.parseInt(scanner.nextLine());
            if (cantidad > jugador.getSaldo()) {
                System.out.println("No puedes apostar esa cantidad porque tu saldo es $" + jugador.getSaldo());
            }
        } while (cantidad > jugador.getSaldo());

        System.out.println("¿A que te gustaria apostar?");
        System.out.println("1. Numero");
        System.out.println("2. Color");
        System.out.print("Por favor, selecciona una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1) {
            System.out.print("Por favor, introduce el numero al que te gustaria apostar (0-36): ");
            int numero = Integer.parseInt(scanner.nextLine());
            mesa.agregarApuesta(jugador, new ApuestaNumero(cantidad, jugador, numero));
        } else if (opcion == 2) {
            System.out.println("¿Que color eliges?");
            System.out.println("1. Rojo");
            System.out.println("2. Negro");
            System.out.print("Por favor, selecciona una opcion: ");
            int colorOpcion = Integer.parseInt(scanner.nextLine());
            String color = colorOpcion == 1 ? "rojo" : "negro";
            mesa.agregarApuesta(jugador, new ApuestaColor(cantidad, jugador, color));
        }
}
    

    private static boolean preguntarSiContinuar() {
        System.out.print("¿Te gustaria jugar otra ronda? (si/no): ");
        String respuesta = scanner.nextLine().toLowerCase();
        if (respuesta.equals("si")) {
            return true;
        } else {
            return false;
        }
    }

    private static void agregarSaldo(Jugador jugador) {
        System.out.print("¿Te gustaria agregar mas dinero a tu saldo? (si/no): ");
        String respuesta = scanner.nextLine().toLowerCase();
        if (respuesta.equals("si")) {
            System.out.print("Por favor, introduce la cantidad que te gustaria agregar: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
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