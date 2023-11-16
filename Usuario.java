import java.util.*;

class Usuario {
    private final String nombre;
    private final String nip;
    private final int saldo;

    public Usuario(String nombre, String nip) {
        this.nombre = nombre;
        this.nip = nip;
        this.saldo = new Random().nextInt(49001) + 1000;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNip() {
        return nip;
    }

    public int getSaldo() {
        return saldo;
    }
}
