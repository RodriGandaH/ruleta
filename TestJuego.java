import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJuego {
    @Test
    public void testGanaApuestaNumero() {
        Jugador jugador = new Jugador("Juan", 1000);
        Apuesta apuesta = new ApuestaNumero(100, jugador, 17);
        jugador.ganaApuesta(apuesta);
        assertEquals(1200, jugador.getSaldo(), "El saldo del jugador después de ganar la apuesta debería ser 1200");
    }

    @Test
    public void testPierdeApuestaColor() {
        Jugador jugador = new Jugador("Juan", 1000);
        Apuesta apuesta = new ApuestaColor(200, jugador, "rojo");
        jugador.pierdeApuesta(apuesta);
        assertEquals(800, jugador.getSaldo(), "El saldo del jugador después de perder la apuesta debería ser 800");
    }
}
