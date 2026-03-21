package logica;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws Exception{
    String[] listaUsuarios = new String[2];
    String[] listaContrasena = new String[2];

    
        Scanner teclado = new Scanner(System.in);
        try {
        guardarUsuarioContra(listaUsuarios, listaContrasena, "Usuarios.txt");
            
        } catch (
             FileNotFoundException e) {
        }
        boolean salir = false;
        while (!salir) {
            System.out.println("1) Menu de Usuarios");
            System.out.println("2) Menu de Analisis");
            System.out.println("3) Salir");
            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                menuUsuarios();

            } else if (opcion == 2) {
                menu_Analisis();
            } else if (opcion == 3) {
                salir = true;
            }

        }

        teclado.close();
    }

    private static void guardarUsuarioContra( String[] listaUsuario, String[] listaConstrasena, String nombreArchivo)  throws FileNotFoundException {
          File archivo = new File(nombreArchivo);
          Scanner sc = new Scanner(archivo);
           int cont = 0;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                String usuario = partes[0];
                String contrasena = partes[1];
                if ( cont >= listaUsuario.length) {
                    listaUsuario[cont]= usuario;
                    listaContrasena[cont] = contrasena;
                }
                cont++;
                }
            }
            sc.close();
    }

    private static void menu_Usuarios(Scanner teclado) {
        Scanner dato = new Scanner(System.in);
        System.out.println("Usuario :");
        String usuario = dato.nextLine();
        System.out.println("Contraseña :");  
        String contraseña = dato.nextLine();

        


    }

    private static void menu_Analisis() {

    }

    /**
     * Metodo que abre el archivo de texto y muestra su contenido
     */
    public static void abrirArchivo2(String nombreArchivo) throws FileNotFoundException {

        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {

                String nombreUsuario = partes[0];
                String fechas = partes[1];
                int CantidadHoras = Integer.parseInt(partes[2]);
                String actividad = partes[3];
                System.out.println("nombre de usuario: " + nombreUsuario + "Fecha: " + fechas + "cantidad de horas invertidas: "
                        + CantidadHoras + "actividad: " + actividad);

            }

        }
        teclado.close();
    }

    /**
     * Metodo que devuelve una lista con la cantidad de horas invertidas por
     * cada usuario
     */
    public static int[] listaCantidadHoras(String nombreArchivo) throws FileNotFoundException {

        int[] listaCantidadHoras = new int[250];
        int cantidad = 0;
        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                int cantidadHoras = Integer.parseInt(partes[2]);
                listaCantidadHoras[cantidad] = cantidadHoras;
                cantidad++;
            }
        }

        teclado.close();

        return listaCantidadHoras;
    }
    /**
     * Metodo que devuelve una lista con las actividades realizadas por cada usuario
     */
     public static String[] listaActividad(String nombreArchivo) throws FileNotFoundException   {
     String[] listaDeActividad = new String[250]; 
     
     int cantidadActi = 0; 
     File archivo = new File(nombreArchivo);
   	 Scanner teclado = new Scanner(archivo);
   	
           while (teclado.hasNextLine()) {
               String linea = teclado.nextLine();
               String[] partes = linea.split(";");
               if (partes.length == 4) {
                   String actividad= partes[4];
                   listaDeActividad[cantidadActi] = actividad;  
    	           cantidadActi++;
               }
           }
           teclado.close();
           
    
    	 
    return listaDeActividad;  	 
     }
     /**
      * Metodo que devuelve una lista con las fechas en las que se realizaron las actividades por cada usuario
      */
     public static String[] listaFecha(String nombreArchivo) throws FileNotFoundException   {
        String[] listaDeFecha = new String[250]; 
        
        int cantidadFecha = 0; 
        File archivo = new File(nombreArchivo);
      	 Scanner teclado = new Scanner(archivo);
      	
              while (teclado.hasNextLine()) {
                  String linea = teclado.nextLine();
                  String[] partes = linea.split(";");
                  if (partes.length == 4) {
                      String fecha= partes[1];
                      listaDeFecha[cantidadFecha] = fecha;  
       	           cantidadFecha++;
                  }
              }
              teclado.close();
              
       
       	 
       return listaDeFecha;  	 
        }
        /**
         * Metodo que permite cambiar un elemento de la lista de actividades, fechas o cantidad de horas invertidas por cada usuario
         */
       public static String[] cambiarElementoLista(String[] lista, int opcionUsuario, Scanner teclado) {
    	 
    	 if (opcionUsuario ==0) {
    		 return lista;
             }
    	 int iteracion = opcionUsuario -1;
    	 
    	 if (iteracion < 0 || iteracion >= lista.length) {
    		 System.out.println("opcion invalida");
    		return lista;
    	 }
    	 System.out.println("Ingresa el nuevo elemento a modificar"); 
    	 String nuevoTipoActividad = teclado.nextLine();
    	 lista[iteracion] =nuevoTipoActividad;
    	 
    	 return lista;
     
}
/**
 * metodo que permite cambiar un elemento de la lista de actividades, fechas o cantidad de horas invertidas por cada usuario
 */
    	 public static int[] cambiarDuracionActividad(int[] listaInt, int opcionUsuario, Scanner teclado ) {
    	 if (opcionUsuario ==0) {
    		 return listaInt;
    		 
    	 }	 
    	 int indice = opcionUsuario -1; 
    	 
    	 if (opcionUsuario >0 || opcionUsuario >=listaInt.length) {
    		 System.out.println("Opcion invalida");
    		 return listaInt; 
    	 }
    	 System.out.println("Ingrese la Duracion de la actividad: ");
    	 int nuevaDuracionActividad = Integer.valueOf(teclado.nextLine());
    	 listaInt[indice] = nuevaDuracionActividad;
    	 return listaInt;
    	 
    		 
    	 }

}