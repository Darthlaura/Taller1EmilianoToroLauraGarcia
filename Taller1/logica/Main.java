package logica;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    private String[] Usuarios = [];
    private String[] contraseña = [];

    public static void main(String[] args)Throws Exception {
        
    }

    private void guardar_Usuariio_contra()Throws Exception {
        Scanner sc = new Scanner(new File("Usuarios"));
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] partes = linea.split(",");
            if (partes.length == 2) {
                String nombre = partes[0].trim();
                String contraseña = partes[1].trim();
                // Aquí puedes almacenar el nombre y la contraseña en tus arreglos o listas
            }
        }
    }
    public void static abrirArchivo2(String nombre) throws fileNotFoundException {

        File archivo = new File(nombre)\
        Scanner teclado = new Scanner(archivo);
        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(",");
            if (partes.length == 2) {
                String nombre = partes[0];
                String contraseña = partes[1].trim();
                // Aquí puedes almacenar el nombre y la contraseña en tus arreglos o listas
            }
        }
        sc.close();
    }




