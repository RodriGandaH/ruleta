public class ApuestaColor extends Apuesta {
    private String colorApostado;

    public ApuestaColor(int cantidad, Jugador jugador, String colorApostado) {
        super(cantidad, jugador);
        this.colorApostado = colorApostado;
    }

    @Override
    public boolean ganador(int resultado) {
        if (colorApostado.equals("rojo")) {
            return resultado % 2 == 0;
        } else if (colorApostado.equals("negro")) {
            return resultado % 2 != 0;
        } else {
            return false;
        }
    }
}