import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private Hotel hotel;
    private ArrayList<Recepcionista> registroRecepcionistas;
    private Administrador administrador;
    private Scanner scanner;
    private static String contraseniaRecepcion = "RECEPCIONISTA123";


    public Sistema(Hotel hotel, Administrador administrador) {
        this.hotel = hotel;
        this.administrador = administrador;
        this.registroRecepcionistas = new ArrayList<>();
        this.scanner = new Scanner(System.in);

    }

    public void realizarBackup(){
        JSONObject hotelJson = hotel.toJson();
        JsonUtiles.uploadJSON(hotelJson,"hotel.json");
    }

    public JSONObject toJson(){
        JSONObject nuevo = new JSONObject();
        nuevo.put("hotel",hotel.toJson());
        JSONArray recepcionistasJson = new JSONArray();
        for (Recepcionista r: registroRecepcionistas){
            recepcionistasJson.put(r.toJson());
        }
        nuevo.put("registroRecepcionistas",recepcionistasJson);
        nuevo.put("administrador",administrador.toJson());

        return nuevo;
    }


    public void agregarRecepcionista(int dni, String nombre) throws DniRepetidoException {
        Recepcionista r = new Recepcionista(dni,nombre);
        if (buscarRecepcionistaXdni(dni)==null) {
            registroRecepcionistas.add(r);
        }else {
            throw new DniRepetidoException("El dni ingresado ya existe en el sistema.");
        }
    }
    public Recepcionista buscarRecepcionistaXdni(int dniRecepcionista){
        for (Recepcionista r : registroRecepcionistas){
            if (r.getDniUsuario() == dniRecepcionista){
                return r;
            }
        }
        return null;
    }

    public void menuPrincipal() {

        boolean salir = false;
        while (!salir) {
            System.out.println("\n-Bienvenido al hotel,  ingrese la opcion que desee: ");
            System.out.println("1. Ingresar como pasajero");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Ingresar como recepcionista ");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                        menuPasajero();

                    break;
                case 2:
                    menuAdmin();
                    break;

                case 3:
                    menuRecepcionista();
                    break;
                case 4:
                    salir = true;
                    break;

            }
            //PONGO REALIZAR BACKUP ACA PARA QUE SIEMPRE SE GUARDEN TODOS LOS CAMBIOS AUTOMATICAMENTE,
            // SIN NECESITAR QUE LO HAGA MANUALMENTE EL ADMIN
            realizarBackup();
        }
    }


        public void menuPasajero()  {
            int dniUsuario = 0;
                System.out.println("Si desea ver sus reservas Ingrese su dni: ");
                dniUsuario = scanner.nextInt();
                scanner.nextLine();
                if (hotel.existePasajeroXdni(dniUsuario)){
                    System.out.println(hotel.mostrarReservasXdni(dniUsuario));
                }else {
                    try {
                        throw new DniNoEncontradoEx("ERROR: EL DNI INGRESADO NO ESTA REGISTRADO EN EL SISTEMA");
                    } catch (DniNoEncontradoEx ex)
                    {
                        System.out.println("DNI INCORRECTO. INGRESE NUEVAMENTE SU DNI: ");
                        dniUsuario = scanner.nextInt();
                        scanner.nextLine();
                        if (!hotel.existePasajeroXdni(dniUsuario)){
                            menuPasajero();
                        }
                    }

                }


            }


        public void menuAdmin() {
        String contraseniaAdmin = "";
            System.out.println("\n---ACCESO DE ADMINISTRADOR---");
            if (administrador.iniciarSesion()) {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n--- MENÚ DE ADMINISTRADOR ---");
                    System.out.println("1. Volver al Menú Principal");
                    System.out.println("2. Agregar nueva habitación");
                    System.out.println("3. Realizar copia de seguridad (Archivo)");
                    System.out.println("4. Agregar nuevo recepcionista");
                    System.out.print("Ingrese una opción: ");

                    if (scanner.hasNextInt()) {
                        int opcion = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcion) {
                            case 1:
                                salir = true;
                                break;

                            case 2:


                                System.out.print("Ingrese el número de la nueva habitación: ");
                                int numHabitacion = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Ingrese el precio por noche: ");
                                double precio = scanner.nextDouble();
                                scanner.nextLine();

                                try {
                                    hotel.agregarHabitacionDisponible(numHabitacion, precio);
                                }catch (NumHabitacionRepetidoEx e){
                                    System.out.println("Numero de habitacion repetido, ingrese otro.");
                                     numHabitacion = scanner.nextInt();
                                    scanner.nextLine();
                                    try
                                    {
                                        hotel.agregarHabitacionDisponible(numHabitacion,precio);
                                    }
                                    catch (NumHabitacionRepetidoEx xe)
                                    {
                                        System.out.println(xe.getMessage());
                                    }

                                }
                                realizarBackup();
                                break;

                            case 3:
                              realizarBackup();
                                System.out.println("Copia de seguridad realizada.");
                                break;


                            case 4:

                                System.out.print("Ingrese el DNI del nuevo recepcionista: ");
                                int dni = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Ingrese el nombre del nuevo recepcionista: ");
                                String nombre = scanner.nextLine();

                                try {
                                    agregarRecepcionista(dni, nombre);
                                } catch (DniRepetidoException e) {
                                    System.out.print("DNI repetido, ingrese otro DNI para el nuevo recepcionista: ");
                                     dni = scanner.nextInt();
                                    scanner.nextLine();
                                    try {
                                        agregarRecepcionista(dni, nombre);
                                    }catch (DniRepetidoException exception){
                                        System.out.println("Dni repetido... regresando al menu principal");
                                    }
                                    }
                                realizarBackup();
                                break;

                            default:
                                System.out.println("Opción inválida. Intente de nuevo.");
                                break;
                        }
                    } else {
                        System.out.println("Entrada inválida. Debe ingresar un número.");
                        scanner.nextLine();
                    }
                }
            } else {
                try {
                    throw new UsuarioInvalidoEx("Contraseña incorrecta: Acceso Denegado");
                }catch (UsuarioInvalidoEx e){
                    System.out.println("Contraseña incorrecta: Acceso Denegado, sera enviado devuelta al menu principal");
                    menuPrincipal();
                }
                }
}



         public void menuRecepcionista(){
        int dniUsuario = 0;
             System.out.println("Bienvenido, ingrese su dni: ");
             dniUsuario = scanner.nextInt();
             scanner.nextLine();
             Recepcionista recepcionista = buscarRecepcionistaXdni(dniUsuario);
             if (recepcionista != null){
                 System.out.println("\n---ACCESO DE RECEPCIONISTA---");
             if(recepcionista.iniciarSesion()){
                 boolean salir = false;
                 while (!salir) {
                     System.out.println("\n---MENÚ DE RECEPCIONISTA---");
                     System.out.println("1. Volver al menu principal");
                     System.out.println("2. Registrar reserva");// excepcion
                     System.out.println("3. Realizar check-in de reserva");
                     System.out.println("4. Realizar check-out de habitacion");
                     System.out.println("5. Ver reservas del hotel");
                     System.out.println("6. Ver habitaciones disponibles");
                     System.out.println("7. Ver habitaciones no disponibles");
                     System.out.println("8. Ver habitaciones ocupadas");
                     System.out.println("9. Ver recaudacion total del hotel");
                     System.out.println("10. Modificar estado de una habitacion");
                     System.out.print("Ingrese una opción: ");

                     if (scanner.hasNextInt()) {
                         int opcion = scanner.nextInt();
                         scanner.nextLine();

                         switch (opcion) {
                             case 1:
                              salir = true;
                                 break;
                             case 2:
                                 System.out.print("Ingrese el número de habitación a reservar: ");
                                 int numHabitacion = scanner.nextInt();
                                 scanner.nextLine();

                                     if (!hotel.corroborarDisponibilidad(numHabitacion)){
                                         try {
                                             throw new HabitacionNoDisponibleEx("Habitacion no disponible");
                                         }catch (HabitacionNoDisponibleEx ex){
                                             System.out.print("La habitacion no esta disponible, elija alguna de las siguientes: ");
                                             hotel.listarHabitacionesDisponibles();
                                             System.out.println("Elija el numero de la habitacion que quiere reservar:");
                                              numHabitacion = scanner.nextInt();
                                             scanner.nextLine();
                                             if (!hotel.corroborarDisponibilidad(numHabitacion)){
                                                 System.out.println("La habitacion seleccionada no esta disponible... regresando al inicio. ");
                                                 menuRecepcionista();
                                             }
                                         }
                                         }else {
                                         System.out.print("Ingrese la cantidad de días de la reserva: ");
                                         int diasReservados = scanner.nextInt();
                                         scanner.nextLine();


                                         System.out.print("Ingrese el DNI del pasajero: ");
                                         int dni = scanner.nextInt();
                                         scanner.nextLine();

                                         System.out.print("Ingrese el nombre del pasajero: ");
                                         String nombre = scanner.nextLine();

                                         System.out.print("Ingrese el apellido del pasajero: ");
                                         String apellido = scanner.nextLine();

                                         System.out.print("Ingrese la nacionalidad del pasajero: ");
                                         String nacionalidad = scanner.nextLine();

                                         System.out.print("Ingrese el domicilio del pasajero: ");
                                         String domicilio = scanner.nextLine();



                                         hotel.agregarReserva(numHabitacion, diasReservados, dni, nombre, apellido, nacionalidad, domicilio);
                                         realizarBackup();
                                         break;
                                 }

                             case 3:
                                 System.out.print("Ingrese el DNI del pasajero para Check-In: ");
                                 int dniPasajeroCheckIn = scanner.nextInt();
                                 scanner.nextLine();

                                 System.out.print("Ingrese el número de habitación reservada: ");
                                 int numHabitacionCheckIn = scanner.nextInt();
                                 scanner.nextLine();


                                 hotel.realizarCheckIn(dniPasajeroCheckIn, numHabitacionCheckIn);
                                 break;
                             case 4:
                                 System.out.print("Ingrese el DNI del pasajero para Check-Out: ");
                                 int dniPasajeroCheckOut = scanner.nextInt();
                                 scanner.nextLine();

                                 System.out.print("Ingrese el número de habitación a desocupar: ");
                                 int numHabitacionCheckOut = scanner.nextInt();
                                 scanner.nextLine();

                                 hotel.realizarCheckOut(dniPasajeroCheckOut, numHabitacionCheckOut);
                                 break;
                             case 5:
                                hotel.mostrarReservas();
                                 break;
                             case 6:
                                hotel.listarHabitacionesDisponibles();
                                 break;
                             case 7:
                                 hotel.listarHabitacionesNoDisponibles();
                                 break;
                             case 8:
                                 hotel.listarHabitacionesOcupadas();
                                 break;
                                 case 9:
                                     System.out.println("Recaudacion total del hotel hasta el momento:  $"+hotel.getRecaudacionTotal());
                                 break;
                             case 10:
                                 seleccionarEstadoHabitacion();
                                 break;
                             default:
                                 System.out.println("Opción inválida. Intente de nuevo.");
                                 break;
                         }
                     } else {
                         System.out.println("Entrada inválida. Debe ingresar un número.");
                         scanner.nextLine();

                     }

                 }
             }
             else{
                 try {
                     throw new UsuarioInvalidoEx("Contraseña incorrecta: Acceso Denegado");
                 }catch (UsuarioInvalidoEx e){
                     System.out.println("Contraseña incorrecta... regresando a menu principal");
                 }
                 }

             }


         }

    public void seleccionarEstadoHabitacion() {

        boolean estadoSeleccionado = false;
        System.out.println("ingrese el numero de la habitacion a modificar: ");
        int numHabitacion = scanner.nextInt();
        scanner.nextLine();
        Habitacion habitacionseleccionada = hotel.buscarHabitacionPorNumero( numHabitacion);

if (habitacionseleccionada != null) {
    while (!estadoSeleccionado) {
        System.out.println("\nSeleccione el estado actual de la habitacion");
        System.out.println("1. Disponible");
        System.out.println("2. Reservada");
        System.out.println("3. Ocupada");
        System.out.println("4. En Limpieza");
        System.out.println("5. En Reparación");
        System.out.println("6. En Desinfección");
        System.out.print("Ingrese el número correspondiente al nuevo estado: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    habitacionseleccionada.setEstado(estadoHabitacion.disponible);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                case 2:
                    habitacionseleccionada.setEstado(estadoHabitacion.reservada);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                case 3:
                    habitacionseleccionada.setEstado(estadoHabitacion.ocupada);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                case 4:
                    habitacionseleccionada.setEstado(estadoHabitacion.enLimpieza);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                case 5:
                    habitacionseleccionada.setEstado(estadoHabitacion.enReparación);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                case 6:
                    habitacionseleccionada.setEstado(estadoHabitacion.enDesinfección);
                    hotel.cambiarHabitacionDeRegistro(habitacionseleccionada);
                    estadoSeleccionado = true;
                    break;
                default:
                    System.out.println("Error, ingrese una opcion del 1 al 6");
                    break;
            }
        } else {
            scanner.nextLine();
        }
    }
}
else {
    System.out.println("El numero de habitacion seleccionado no existe en el sistema");
}

    }


    /*
    public Hotel  descargarBackup(String archivo) throws FileNotFoundException {
        String contenido = JsonUtiles.downloadJSON(archivo);
        JSONObject objeto = new JSONObject(contenido);
        Hotel hotel1 = new Hotel("UTN");

        Registro<Habitacion> habitacionesDisponibles = new Registro<>();
        Registro<Habitacion> habitacionesNoDisponibles = new Registro<>();
        Registro <Reserva> registroReservas = new Registro<>();
        ArrayList<Pasajero> pasajeros = new ArrayList<>();

        JSONArray habitacionesDisponiblesArray = objeto.getJSONArray("habitacionesDisponibles");
        JSONArray habitacionesNoDisponiblesArray = objeto.getJSONArray("habitacionesNoDisponibles");
        JSONArray registroReservasArray = objeto.getJSONArray("registroReservas");
        JSONArray pasajerosArray = objeto.getJSONArray("pasajeros");

        for (int i = 0; i < habitacionesDisponiblesArray.length(); i++){
            JSONObject habitacion = habitacionesDisponiblesArray.getJSONObject(i);
            int numHabitacion = habitacion.getInt("numHabitacion");
            double precioXnoche = habitacion.getDouble("precioXnoche");
            Habitacion nuevaHabitacion = new Habitacion(numHabitacion,precioXnoche);
            habitacionesDisponibles.agregarRegistro(nuevaHabitacion);
        }

        for (int j = 0; j < habitacionesNoDisponiblesArray.length(); j++){
            JSONObject habitacion = habitacionesNoDisponiblesArray.getJSONObject(j);
            int numHabitacion = habitacion.getInt("numHabitacion");
            double precioXnoche = habitacion.getDouble("precioXnoche");
            Habitacion nuevaHabitacion = new Habitacion(numHabitacion, precioXnoche);
            habitacionesNoDisponibles.agregarRegistro(nuevaHabitacion);
        }

        for (int k = 0; k < registroReservasArray.length(); k++){
            JSONObject reserva = registroReservasArray.getJSONObject(k);
            JSONObject habitacion = reserva.getJSONObject("habitacion");
            int idReserva = reserva.getInt("idReserva");

            int numHabitacion = habitacion.getInt("numHabitacion");
            double precioXnoche = habitacion.getDouble("precioXnoche");
            Habitacion nuevaHabitacion = new Habitacion(numHabitacion, precioXnoche);

            int diasReservados = reserva.getInt("diasReservados");
             double precioApagar = reserva.getDouble("precioApagar");
             boolean checkInRealizado = reserva.getBoolean("checkInRealizado");

             for (int i = 0; i< pasajerosArray.length(); i++){
                 JSONObject pasajero = pasajerosArray.getJSONObject(i);
                 int dniUsuario = pasajero.getInt("dniUsuario");
                 String nombre = pasajero.getString("nombre");
                  String apellido = pasajero.getString("apellido");
                  String nacionalidad = pasajero.getString("nacionalidad");
                  String domicilio = pasajero.getString("domicilio");
                  Pasajero nuevoPasajero = new Pasajero(dniUsuario, nombre, apellido, nacionalidad, domicilio);
                  pasajeros.add(nuevoPasajero);
             }
             Reserva nuevaReserva = new Reserva(nuevaHabitacion,diasReservados);
             nuevaReserva.setPasajeros(pasajeros);
             nuevaReserva.setIdReserva(idReserva);
             nuevaReserva.setPrecioApagar(precioApagar);
             nuevaReserva.setCheckInRealizado(checkInRealizado);
        }


        return hotel1;
    }

     */

        }


