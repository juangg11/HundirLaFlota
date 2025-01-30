import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {

    private static final String FILE_PATH = "datos/datos.csv";

    public static void imprimir(String mensaje) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(mensaje + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leer(int numeroLinea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            int contador = 0;
            while ((linea = reader.readLine()) != null) {
                if (contador == numeroLinea) {
                    return linea;
                }
                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void inicializarTablero() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 10; columna++) {
                    writer.write(fila + "" + columna + ",0" + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> leerCSV() {
        List<String> valores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                for (String valor : partes) {
                    valores.add(valor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valores;
    }
    public void guardarCSV(List<String> valores) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < valores.size(); i++) {
                writer.write(valores.get(i)+",");
                i++;
                writer.write(valores.get(i) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}