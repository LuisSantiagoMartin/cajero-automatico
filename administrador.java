import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

class Administrador {
    private static final String LOG_FILE = "logs.txt";
    private static final String BILLETES_FILE = "billetes.dat";

    public void menuAdministrador() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAcciones disponibles como Administrador:");
            System.out.println("1. Mostrar listado de acciones en el log");
            System.out.println("2. Mostrar cantidad de billetes restantes");
            System.out.println("3. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarLog();
                case 2 -> mostrarCantidadBilletes();
                case 3 -> System.exit(0);
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
private void mostrarLog() {
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {
                String linea;
                
                System.out.println("\nListado de acciones en el log:");
                while ((linea = bufferedReader.readLine()) != null) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
        }
    }

    private void mostrarCantidadBilletes() {
        CajeroAutomático cajero = new CajeroAutomático(BILLETES_FILE);
        Map<String, Integer> billetes = cajero.getBilletes();

        System.out.println("\nCantidad de billetes restantes:");
        System.out.println("Denominación\tCantidad");
        for (Map.Entry<String, Integer> entry : billetes.entrySet()) {
            System.out.println(entry.getKey() + "\t\t\t" + entry.getValue());
        }
    }
}
