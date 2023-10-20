public class Jugador {
    private String nombre;
    private int saldo;

    public Jugador(String nombre, int saldoInicial) {
        this.nombre = nombre;
        this.saldo = saldoInicial;
    }

    public void ganaApuesta(Apuesta apuesta) {
        this.saldo += apuesta.cantidad * 2; 
    }

    public void pierdeApuesta(Apuesta apuesta) {
        this.saldo -= apuesta.cantidad;
    }

    public void agregarSaldo(int cantidad) {
        this.saldo += cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }
}
