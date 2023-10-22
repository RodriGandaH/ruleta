

public abstract class Apuesta
{
    protected int cantidad;
    protected Jugador jugador;

    public Apuesta(int cantidad, Jugador jugador) {
        this.cantidad = cantidad;
        this.jugador = jugador;
    }

    public abstract boolean ganador(int resultado);
}
