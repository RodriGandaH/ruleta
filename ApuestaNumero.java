public class ApuestaNumero extends Apuesta {
    private int numeroApostado;

    public ApuestaNumero(int cantidad, Jugador jugador, int numeroApostado) {
        super(cantidad, jugador);
        this.numeroApostado = numeroApostado;
    }

    @Override
    public boolean ganador(int resultado) {
        return resultado == numeroApostado;
    }
}