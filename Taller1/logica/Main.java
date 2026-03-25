package logica;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
        // listas
        String[] listaUsuarios = new String[300];
        String[] listaContrasena = new String[300];
        String[] listaActividad = new String[300];
        String[] listaNombreRegistro = new String[300];
        String[] listaFecha = new String[300];
        int[] listaCantidadHoras = new int[300];
        // contador de registros
        int cantidadRegistros = 0;

        Scanner teclado = new Scanner(System.in);
        // control de errores
        try {
            listaNombreRegistro = listaNombreRegistrto("Registros.txt");
            listaFecha = listaFecha("Registros.txt");
            listaCantidadHoras = listaCantidadHoras("Registros.txt");
            listaActividad = listaActividad("Registros.txt");
            cantidadRegistros = contarRegistros("Registros.txt");
            guardarUsuarioContra(listaUsuarios, listaContrasena, "Usuarios.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontro un archivo.");
        }

        boolean salir = false;

        while (!salir) {
            System.out.println("1) Menu de Usuarios");
            System.out.println("2) Menu de Analisis");
            System.out.println("3) Salir");
            int opcion = Integer.parseInt(teclado.nextLine());

            if (opcion == 1) {
                int indiceUsuario = menuUsuarios(teclado, listaUsuarios, listaContrasena); // retorna el indice del
                                                                                           // usuario en la lista de
                                                                                           // usuarios y contraseñas,
                // o -1 si no encuentra al usuario o la contraseña es incorrecta

                if (indiceUsuario != -1) {
                    // se crea una variable para almacenar el nombre del usuario actual
                    String usuarioActual = listaUsuarios[indiceUsuario];
                    boolean salirMenuUsuario = false;

                    while (!salirMenuUsuario) {
                        // opcionMenu retorna un int ingresado por teclado
                        int opcionUsuario = opcionMenu(teclado);

                        if (opcionUsuario == 1) {
                            // registratr actividad
                            cantidadRegistros = registrarActividad(listaNombreRegistro, listaFecha, listaCantidadHoras,
                                    listaActividad, cantidadRegistros, usuarioActual, teclado);

                        } else if (opcionUsuario == 2) {
                            // modificar actividad
                            modificarActividad(listaNombreRegistro, listaFecha, listaCantidadHoras, listaActividad,
                                    cantidadRegistros, usuarioActual, teclado);

                        } else if (opcionUsuario == 3) {
                            // eliminar actividad
                            cantidadRegistros = eliminarActividad(listaNombreRegistro, listaFecha, listaCantidadHoras,
                                    listaActividad, cantidadRegistros, usuarioActual, teclado);

                        } else if (opcionUsuario == 4) {
                            // cambiar contraseña
                            System.out.println("Ingrese la nueva contraseña:");
                            String nuevaContrasena = teclado.nextLine();
                            listaContrasena[indiceUsuario] = nuevaContrasena;
                            System.out.println("Contraseña cambiada con exito.");

                        } else if (opcionUsuario == 5) {
                            salirMenuUsuario = true;

                        } else {
                            System.out.println("Opcion invalida.");
                        }
                    }
                }

            } else if (opcion == 2) {
                menu_Analisis();

            } else if (opcion == 3) {
                salir = true;

            } else {
                System.out.println("Opcion invalida.");
            }
        }

        teclado.close();
    }

    // Metodos menu usuario
    /**
     * metodo que muestra el menu de opciones para el usuario y retorna la opcion
     * ingresada por teclado
     * 
     * @param teclado
     * @return
     */
    public static int opcionMenu(Scanner teclado) {

        System.out.println("1) Registrar actividad");
        System.out.println("2) Modificar actividad");
        System.out.println("3) Eliminar actividad");
        System.out.println("4) cambiar contraseña");
        System.out.println("5) Salir");
        System.out.println("Que actividad desea realizar? :");
        int opcion = Integer.parseInt(teclado.nextLine());

        return opcion;
    }

    /**
     * Metodo que abre el archivo de texto y guarda los usuarios y contraseñas en
     * dos listas diferentes
     * 
     * @param listaUsuario
     * @param listaContrasena
     * @param nombreArchivo
     * @throws FileNotFoundException
     */
    private static void guardarUsuarioContra(String[] listaUsuario, String[] listaContrasena, String nombreArchivo)
            throws FileNotFoundException {
        File archivo = new File(nombreArchivo);
        Scanner sc = new Scanner(archivo);
        int cont = 0;
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 2) {
                String usuario = partes[0];
                String contrasena = partes[1];
                if (cont < listaUsuario.length) {
                    listaUsuario[cont] = usuario;
                    listaContrasena[cont] = contrasena;
                }
                cont++;
            }
        }
        sc.close();
    }

    /**
     * Metodo que muestra el menu de usuarios y permite ingresar un usuario y
     * contraseña para acceder al sistema
     * 
     * @param teclado
     * @param listaUsuario
     * @param listaContrasena
     */
    private static int menuUsuarios(Scanner teclado, String[] listaUsuario, String[] listaContrasena) {
        System.out.println("Usuario :");
        String usuario = teclado.nextLine();
        System.out.println("Contraseña :");
        String contraseña = teclado.nextLine();
        int indice = 0; // pocicion del usuario en la lista de usuarios y contraseñas
        while (indice < listaUsuario.length && listaUsuario[indice] != null) {
            if (usuario.equalsIgnoreCase(listaUsuario[indice])
                    && contraseña.equalsIgnoreCase(listaContrasena[indice])) {
                System.out.println("Acceso correcto!");
                System.out.println("Bienvenido " + usuario);
                return indice;
            }
            indice++;
        }
        System.out.println("Usuario o contraseña incorrectos");
        return -1; // retorna -1 si no encuentra al usuario o la contraseña es incorrecta
    }

    private static void menu_Analisis() {

    }

    /**
     * metodo que abre el archivo Registros.txt, lee cada linea separa por partes e
     * imprime el contenido
     * 
     * @param nombreArchivo
     * @throws FileNotFoundException
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
                System.out.println(
                        "nombre de usuario: " + nombreUsuario + "Fecha: " + fechas + "cantidad de horas invertidas: "
                                + CantidadHoras + "actividad: " + actividad);

            }

        }
        teclado.close();
    }

    /**
     * Metodo que devuelve una lista con la cantidad de horas invertidas por cada
     * usuario
     * 
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
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
     * metodo que devuelve una lista con el tipo de actividad realizada por cada
     * usuario
     * 
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     */
    public static String[] listaActividad(String nombreArchivo) throws FileNotFoundException {
        String[] listaDeActividad = new String[300];

        int cantidadActi = 0;
        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                String actividad = partes[3];
                listaDeActividad[cantidadActi] = actividad;
                cantidadActi++;
            }
        }
        teclado.close();

        return listaDeActividad;
    }

    /**
     * metodo que devuelve una lista con las fechas de cada actividad realizada por
     * cada usuario
     * 
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     */
    public static String[] listaFecha(String nombreArchivo) throws FileNotFoundException {
        String[] listaDeFecha = new String[250];

        int cantidadFecha = 0;
        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                String fecha = partes[1];
                listaDeFecha[cantidadFecha] = fecha;
                cantidadFecha++;
            }
        }
        teclado.close();

        return listaDeFecha;
    }

    /**
     * metodo que devuelve una lista con el nombre de usuario de cada actividad
     * realizada por cada usuario
     * 
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     */
    public static String[] listaNombreRegistrto(String nombreArchivo) throws FileNotFoundException {
        String[] listaNombre = new String[300];

        int cantidad = 0;
        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");

            if (partes.length == 4) {
                String usuario = partes[0];
                listaNombre[cantidad] = usuario;
                cantidad++;
            }
        }
        teclado.close();

        return listaNombre;
    }

    /**
     * metodo que permite cambiar un elemento de las listas
     * 
     * @param lista
     * @param opcionUsuario
     * @param teclado
     * @return
     */
    public static String[] cambiarElementoLista(String[] lista, int opcionUsuario, Scanner teclado) {

        if (opcionUsuario == 0) {
            return lista;
        }
        int iteracion = opcionUsuario - 1;

        if (iteracion < 0 || iteracion >= lista.length) {
            System.out.println("opcion invalida");
            return lista;
        }
        System.out.println("Ingresa el nuevo elemento a modificar");
        String nuevoTipoActividad = teclado.nextLine();
        lista[iteracion] = nuevoTipoActividad;

        return lista;
    }

    /**
     * metodo que permite cambiar un elemento de la lista de cantidad de horas
     * invertidas
     * 
     * @param listaInt
     * @param opcionUsuario
     * @param teclado
     * @return
     */
    public static int[] cambiarDuracionActividad(int[] listaInt, int opcionUsuario, Scanner teclado) {
        if (opcionUsuario == 0) {
            return listaInt;

        }
        int indice = opcionUsuario - 1;

        if (opcionUsuario > 0 || opcionUsuario >= listaInt.length) {
            System.out.println("Opcion invalida");
            return listaInt;
        }
        System.out.println("Ingrese la Duracion de la actividad: ");
        int nuevaDuracionActividad = Integer.valueOf(teclado.nextLine());
        listaInt[indice] = nuevaDuracionActividad;
        return listaInt;

    }

    /**
     * metodo que cuenta la cantidad de registros en el archivo Registros.txt para
     * llevar un control de la cantidad de actividades registradas
     * 
     * @param nombreArchivo
     * @return
     * @throws FileNotFoundException
     */
    public static int contarRegistros(String nombreArchivo) throws FileNotFoundException {
        int cantidadRegistros = 0;
        File archivo = new File(nombreArchivo);
        Scanner teclado = new Scanner(archivo);

        while (teclado.hasNextLine()) {
            String linea = teclado.nextLine();
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                cantidadRegistros++;
            }
        }

        teclado.close();

        return cantidadRegistros;
    }

    /**
     * metodo que permite registrar una nueva actividad, y aumentar el contador de
     * registros
     * 
     * @param listaNombreRegistro
     * @param listaFecha
     * @param listaCantidadHoras
     * @param listaActividad
     * @param cantidadRegistros
     * @param usuarioActual
     * @param teclado
     * @return
     */
    public static int registrarActividad(String[] listaNombreRegistro, String[] listaFecha, int[] listaCantidadHoras,
            String[] listaActividad, int cantidadRegistros, String usuarioActual, Scanner teclado) {

        if (cantidadRegistros >= listaActividad.length) { // si la cantidad de registro es mayor o igual a la longitud
                                                          // de la lista de actividades no se pueden registrar más
                                                          // actividades
            System.out.println("No se pueden registrar más actividades. Capacidad máxima alcanzada.");
            return cantidadRegistros;
        }
        // se le pedira al usuario que ingrese los datos para agregarlos a cada una de
        // las listas y guardar la nuev actividad
        System.out.println("Ingrese la fecha de la actividad: ");
        String fechaActividad = teclado.nextLine();
        System.out.println("Ingrese la cantidad de horas: ");
        int cantidadHoras = Integer.valueOf(teclado.nextLine());
        System.out.println("Ingrese el tipo de actividad realizada: ");
        String tipoActividad = teclado.nextLine();

        listaNombreRegistro[cantidadRegistros] = usuarioActual;
        listaFecha[cantidadRegistros] = fechaActividad;
        listaCantidadHoras[cantidadRegistros] = cantidadHoras;
        listaActividad[cantidadRegistros] = tipoActividad;
        cantidadRegistros++;
        System.out.println("Actividad registrada con exito.");

        return cantidadRegistros;
    }

    /**
     * metodo que muestra las actividades registradas por el usuario actual: fecha,
     * cantidad de horas invertidas y tipo de actividad
     * 
     * @param listaNombreRegistro
     * @param listaFecha
     * @param listaCantidadHoras
     * @param listaActividad
     * @param cantidadRegistros
     * @param usuarioActual
     * @param teclado
     */
    public static void mostrarActividadesUsuario(String[] listaNombreRegistro, String[] listaFecha,
            int[] listaCantidadHoras, String[] listaActividad, int cantidadRegistros, String usuarioActual,
            Scanner teclado) {

        System.out.println("0) Regresar");
        int n = 1;
        for (int i = 0; i < cantidadRegistros; i++) {
            if (listaNombreRegistro[i].equals(usuarioActual)) {
                System.out.println(n + ")" + listaNombreRegistro[i] + ";" + listaFecha[i] + ";" + listaCantidadHoras[i]
                        + ";" + listaActividad[i]);
                n++;
            }

        }
    }

    /**
     * metodo que busca el indice real de la actividad seleccionada por el usuario
     * para modificar o eliminar, sabiendo que es n+1 para buscar la actividad
     * 
     * @param listaNombreRegistro
     * @param listaFecha
     * @param listaCantidadHoras
     * @param listaActividad
     * @param cantidadRegistros
     * @param usuarioActual
     * @param opcionUsuario
     * @return
     */
    public static int buscarIndiceRealActividad(String[] listaNombreRegistro, String[] listaFecha,
            int[] listaCantidadHoras, String[] listaActividad, int cantidadRegistros, String usuarioActual,
            int opcionUsuario) {
        int contador = 0;

        for (int i = 0; i < cantidadRegistros; i++) {
            if (listaNombreRegistro[i].equalsIgnoreCase(usuarioActual)) {
                contador++;
                if (contador == opcionUsuario) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * metodo que permite modificar la fecha, cantidad de horas o tipo de actividad
     * de una actividad registrada por el usuario actual
     * 
     * @param listaNombreRegistro
     * @param listaFecha
     * @param listaCantidadHoras
     * @param listaActividad
     * @param cantidadRegistros
     * @param usuarioActual
     * @param teclado
     */
    public static void modificarActividad(String[] listaNombreRegistro, String[] listaFecha, int[] listaCantidadHoras,
            String[] listaActividad, int cantidadRegistros, String usuarioActual, Scanner teclado) {
        mostrarActividadesUsuario(listaNombreRegistro, listaFecha, listaCantidadHoras, listaActividad,
                cantidadRegistros, usuarioActual, teclado);

        System.out.println("Seleccione la actividad que desea modificar: ");
        int opcionActividad = Integer.valueOf(teclado.nextLine());
        if (opcionActividad == 0) {

            return; // vuelve al menu anterior
        }
        // usamos opcionActividad para buscar el indice real de la actividad en las
        // listas
        int indiceRealActividad = buscarIndiceRealActividad(listaNombreRegistro, listaFecha, listaCantidadHoras,
                listaActividad, cantidadRegistros, usuarioActual, opcionActividad);
        if (indiceRealActividad == -1) {
            System.out.println("Opcion invalida");
            return;
        }

        System.out.println("0) Regresar ");
        System.out.println("1) Modificar fecha de la actividad: ");
        System.out.println("2 Duracion de la actividad: ");
        System.out.println("3) Modificar tipo de actividad: ");
        int opcionModificar = Integer.valueOf(teclado.nextLine());
        if (opcionModificar == 0) {
            return; // regresar el menu
        } else if (opcionModificar == 1) {
            // modificar fecha
            System.out.println("Ingrese la nueva fecha de la actividad: ");
            String nuevaFecha = teclado.nextLine();
            listaFecha[indiceRealActividad] = nuevaFecha;
            System.out.println("Fecha de la actividad modificada con exito.");

        } else if (opcionModificar == 2) {
            // modificar duración
            System.out.println("Ingrese la nueva duracion de la actividad: ");
            int nuevaDuracion = Integer.valueOf(teclado.nextLine());
            listaCantidadHoras[indiceRealActividad] = nuevaDuracion;
            System.out.println("Duracion de la actividad modificada con exito.");
        } else if (opcionModificar == 3) {
            // modificar tipo de actividad
            System.out.println("Ingrese el nuevo tipo de actividad: ");
            String nuevoTipoActividad = teclado.nextLine();
            listaActividad[indiceRealActividad] = nuevoTipoActividad;
            System.out.println("Tipo de actividad modificado con exito.");
        } else {
            System.out.println("Opcion invalida");
            return;
        }
        System.out.println("Actividad modificada con exito.");

    }

    /**
     * metodo que permite eliminar una actividad registrada por el usuario actual, y
     * disminuir el contador de registros
     * 
     * @param listaNombreRegistro
     * @param listaFecha
     * @param listaCantidadHoras
     * @param listaActividad
     * @param cantidadRegistros
     * @param usuarioActual
     * @param teclado
     * @return
     */
    public static int eliminarActividad(String[] listaNombreRegistro, String[] listaFecha, int[] listaCantidadHoras,
            String[] listaActividad, int cantidadRegistros, String usuarioActual, Scanner teclado) {

        mostrarActividadesUsuario(listaNombreRegistro, listaFecha, listaCantidadHoras, listaActividad,
                cantidadRegistros, usuarioActual, teclado);

        System.out.println("Seleccione la actividad a eliminar:");
        int opcionActividad = Integer.parseInt(teclado.nextLine());

        if (opcionActividad == 0) {
            return cantidadRegistros;
        }

        int indiceReal = buscarIndiceRealActividad(listaNombreRegistro, listaFecha, listaCantidadHoras, listaActividad,
                cantidadRegistros, usuarioActual, opcionActividad);

        if (indiceReal == -1) {
            System.out.println("Opcion invalida");
            return cantidadRegistros;
        }

        for (int i = indiceReal; i < cantidadRegistros - 1; i++) {
            listaNombreRegistro[i] = listaNombreRegistro[i + 1];
            listaFecha[i] = listaFecha[i + 1];
            listaCantidadHoras[i] = listaCantidadHoras[i + 1];
            listaActividad[i] = listaActividad[i + 1];
        }

        listaNombreRegistro[cantidadRegistros - 1] = null;
        listaFecha[cantidadRegistros - 1] = null;
        listaCantidadHoras[cantidadRegistros - 1] = 0;
        listaActividad[cantidadRegistros - 1] = null;

        cantidadRegistros--;

        System.out.println("Actividad eliminada con exito");
        return cantidadRegistros;
    }

}