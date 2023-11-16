import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

class CajeroAutomático {
    private Map<String, Integer> billetes;
    private final String billetesFile;

    public CajeroAutomático(String billetesFile) {
        this.billetesFile = billetesFile;
        cargarBilletes();
    }

    private void cargarBilletes() {
        try {
            FileInputStream fileInputStream = new FileInputStream(billetesFile);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                billetes = (HashMap<String, Integer>) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            billetes = new HashMap<>();
            inicializarBilletes();
        }
    }

    private void guardarBilletes() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(billetesFile);
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(billetes);
            }
        } catch (IOException e) {
        }
    }

    private void inicializarBilletes() {
        billetes.put("100", 100);
        billetes.put("200", 100);
        billetes.put("500", 20);
        billetes.put("1000", 10);
        guardarBilletes();
    }
    public void consultarSaldo(Usuario usuario) {
        System.out.println("Saldo actual de " + usuario.getNombre() + ": $" + usuario.getSaldo());
    }

    public boolean retirarEfectivo(Usuario usuario, int cantidad) {
int montoMinimo = Math.min(usuario.getSaldo(), calcularMontoMinimoBilletes());
        System.out.println("Monto mínimo a retirar: $" + montoMinimo);

        if (cantidad > usuario.getSaldo() || cantidad > montoMinimo) {
            System.out.println("Fondos insuficientes o cantidad incorrecta. Operación cancelada.");
            return false;
        }

        if (!verificarBilletesDisponibles(cantidad)) {
            System.out.println("No hay billetes disponibles para entregar esa cantidad. Operación cancelada.");
            return false;
        }

        actualizarBilletes(cantidad);
        usuario = new Usuario(usuario.getNombre(), usuario.getNip()); // Actualizar saldo del usuario
        usuario = new Usuario(usuario.getNombre(), usuario.getNip()); // Actualizar saldo del usuario

        guardarBilletes();
        System.out.println("Retiro exitoso. Nuevo saldo: $" + usuario.getSaldo());
        return true;
    }

    private int calcularMontoMinimoBilletes() {
        return billetes.get("100") * 100 + billetes.get("200") * 200 + billetes.get("500") * 500 + billetes.get("1000") * 1000;
    }

    private boolean verificarBilletesDisponibles(int cantidad) {
        return cantidad <= calcularMontoMinimoBilletes();
    }

    private void actualizarBilletes(int cantidad) {

        billetes.put("100", billetes.get("100") - cantidadBilletes("100", cantidad));
        billetes.put("200", billetes.get("200") - cantidadBilletes("200", cantidad));
        billetes.put("500", billetes.get("500") - cantidadBilletes("500", cantidad));
        billetes.put("1000", billetes.get("1000") - cantidadBilletes("1000", cantidad));
    }

    private int cantidadBilletes(String denominacion, int cantidad) {
        return cantidad / Integer.parseInt(denominacion);
    }

    Map<String, Integer> getBilletes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }





}
