import java.util.*;
public class Mesa {
    private Map<Jugador, List<Apuesta>> apuestas;

    public Mesa() {
        this.apuestas = new HashMap<>();
    }

    public void agregarApuesta(Jugador jugador, Apuesta apuesta) {
        if (!apuestas.containsKey(jugador)) {
            apuestas.put(jugador, new ArrayList<>());
        }
        apuestas.get(jugador).add(apuesta);
    }

    public void resolverApuestas(Ruleta ruleta) {
        int resultado = ruleta.girar();
        for (Map.Entry<Jugador, List<Apuesta>> entry : apuestas.entrySet()) {
            Jugador jugador = entry.getKey();
            for (Apuesta apuesta : entry.getValue()) {
                if (apuesta.ganador(resultado)) {
                    jugador.ganaApuesta(apuesta);
                } else {
                    jugador.pierdeApuesta(apuesta);
                }
            }
        }
        apuestas.clear(); 
    }
}