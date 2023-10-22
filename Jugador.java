public class Jugador implements IApostador {
    private String nombre;
    private int saldo;

    public Jugador(String nombre, int saldoInicial) {
        this.nombre = nombre;
        this.saldo = saldoInicial;
    }

    @Override
    public void ganaApuesta(Apuesta apuesta) {
        this.saldo += apuesta.cantidad * 2; 
    }

    @Override
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