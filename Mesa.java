import java.util.*;

public class Mesa {
    private Map<Jugador, List<Apuesta>> apuestas;

    public Mesa() {
        this.apuestas = new HashMap<>();
    }

    public void agregarApuesta(Jugador jugador, Apuesta apuesta) {
        apuestas.computeIfAbsent(jugador, k -> new ArrayList<>()).add(apuesta);
    }

    public void resolverApuestas(Ruleta ruleta) {
        int resultado = ruleta.girar();
        Jugador jugador = apuestas.keySet().iterator().next();
        for (Apuesta apuesta : apuestas.get(jugador)) {
            if (apuesta.ganador(resultado)) {
                jugador.ganaApuesta(apuesta);
            } else {
                jugador.pierdeApuesta(apuesta);
            }
        }
        apuestas.clear(); 
    }
}
