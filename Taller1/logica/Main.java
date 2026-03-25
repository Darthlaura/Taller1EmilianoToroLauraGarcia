package logica;

<<<<<<< Updated upstream
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
=======
>>>>>>> Stashed changes

public class Main {


    public static void main(String[] args) throws Exception{
    String[] listaUsuarios = new String[2];
    String[] listaContrasena = new String[2];
    int []   listaCantidadHoras = new int[250];
    String [] listafecha = new String[250];
    Scanner teclado = new Scanner(System.in);

    try {
        listaCantidadHoras = listaCantidadHoras("Registros.txt");
        listafecha = listaFecha("Registros.txt");
        guardarUsuarioContra(listaUsuarios, listaContrasena, "Usuarios.txt");



    }catch (FileNotFoundException e) { 
    
        }
        boolean salir = false;
        while (!salir) {
            System.out.println("1) Menu de Usuarios");
            System.out.println("2) Menu de Analisis");
            System.out.println("3) Salir");
            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion == 1) {
                menuUsuarios(teclado, listaUsuarios, listaContrasena);
                int opcionUsuario = opcionMenu(teclado);
                    if (opcionUsuario == 1) {
                        abrirArchivo2("Registros.txt");
                    } else if (opcionUsuario == 2) {
                        System.out.println("Cual actividad desea modificar? :");
                        int actividad = teclado.nextInt();
                        teclado.nextLine();
                        cambiarElementoLista(listafecha, actividad, teclado);
                    } else if (opcionUsuario == 3) {
                        System.out.println("Cual actividad desea eliminar? :");
                        int actividad = teclado.nextInt();
                        teclado.nextLine();
                        cambiarDuracionActividad(listaCantidadHoras, actividad, teclado);
                    } else if (opcionUsuario == 4) {
                        cambiarElementoLista(listaUsuarios, opcionUsuario, teclado);
                    } else if (opcionUsuario == 5) {
                        salir = true;
                    }
                


            } else if (opcion == 2) {
                menu_Analisis();

            } else if (opcion == 3) {
                salir = true;
            }

        }

        teclado.close();
    }
    public static int opcionMenu(Scanner teclado){
        System.out.println("Que actividad desea realizar? :");
        int opcion = teclado.nextInt();
        do{
           System.out.println("1) Registrar actividad");
           System.out.println("2) Modificar actividad");
           System.out.println("3) Eliminar actividad");
           System.out.println("4) cambiar contraseña");
           System.out.println("5) Salir");
           if (opcion <1 || opcion >5) {
        	   System.out.println("Opcion invalida");
           }

      
      }while (opcion <1 || opcion >5); 


    return opcion;
    }



    /**
     * Metodo que abre el archivo de texto y guarda los usuarios y contraseñas en dos listas diferentes
     * @param listaUsuario
     * @param listaContrasena
     * @param nombreArchivo
     * @throws FileNotFoundException
     */
    private static void guardarUsuarioContra( String[] listaUsuario, String[] listaContrasena, String nombreArchivo)  throws FileNotFoundException {
          File archivo = new File(nombreArchivo);
          Scanner sc = new Scanner(archivo);
           int cont = 0;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                String usuario = partes[0];
                String contrasena = partes[1];
                if ( cont < listaUsuario.length) {
                    listaUsuario[cont]= usuario;
                    listaContrasena[cont] = contrasena;
                }
                cont++;
                }
            }
            sc.close();
    }
    /**
     * Metodo que muestra el menu de usuarios y permite ingresar un usuario y contraseña para acceder al sistema
     * @param teclado
     * @param listaUsuario
     * @param listaContrasena
     */
    private static void menuUsuarios(Scanner teclado, String[] listaUsuario, String[] listaContrasena) {
        System.out.println("Usuario :");
        String usuario = teclado.nextLine();
        System.out.println("Contraseña :");  
        String contraseña = teclado.nextLine();
            if (usuario.equals(listaUsuario[0]) && contraseña.equals(listaContrasena[0]) || usuario.equals(listaUsuario[1]) && contraseña.equals(listaContrasena[1])) {
                System.out.println("Acceso correcto " + usuario);
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }


        


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