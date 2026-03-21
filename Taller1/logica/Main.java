package logica;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    private static String[] Usuarios = new String[2];
    private static String[] Contraseña = new String[2];

    public static void main(String[] args) Throws Exception {
        Scanner seleccion = new Scanner(System.in);
        guardar_Usuariio_contra();
        boolean salir = false;
        while(!salir){
        System.out.println("1) Menu de Usuarios");
        System.out.println("2) Menu de Analisis");
        System.out.println("3) Salir");
        int opcion = seleccion.nextInt();
        seleccion.nextLine();

        if(opcion==1){
            menu_Usuarios();    
        }
        else if(opcion==2){
            menu_Analisis();
        }
        else if(opcion==3){
            salir=true;
        }

        }

      seleccion.close();  
    }

    private static void guardar_Usuariio_contra()Throws Exception {
        Scanner sc = new Scanner(new File("Usuarios"));
        cont=0;
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] partes = linea.split(";");
            String usuario = partes[0];
            String contraseña = partes[1];

            if cont=0{
            Usuarios[0] = usuario;
            Contraseña[0] = contraseña;
            }else{
                Usuarios[cont] = usuario;
                Contraseña[cont] = contraseña;
            }

            cont+=1;
            } sc.close();
        }
    }

    private static void menu_Usuarios();{

    }

    private static void menu_Analisis() {

    }   


    private static void abrirArchivo2() throws FileNotFoundException {

        Scanner teclado = new Scanner(new File("Registros.txt"));
        int [] CantidadHoras = new int[3];

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                String nombreUsuario = partes[0];
                String fechas= partes[1];
                int CantidadHoras= Integer.parseInt(partes[2]);
                String actividad= partes[3];
            }
        }
        teclado.close();
    }  

    private static class fileNotFoundException extends Exception {

        public fileNotFoundException() {
        }
    }




