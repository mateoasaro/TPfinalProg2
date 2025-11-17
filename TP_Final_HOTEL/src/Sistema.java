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
    }

    public void agregarRecepcionista(int dni, String nombre){
        Recepcionista r = new Recepcionista(dni,nombre);
        registroRecepcionistas.add(r);
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
        }
    }


        public void menuPasajero(){
            int dniUsuario = 0;
                System.out.println("Si desea ver sus reservas Ingrese su dni: ");
                dniUsuario = scanner.nextInt();
                scanner.nextLine();

                hotel.mostrarReservasXdni(dniUsuario);
                //primero llamar funcion existeDni y lanzar excepcion;

            }


        public void menuAdmin() /* throws UsuarioInvalidoEx */{
        String contraseniaAdmin = "";
            System.out.println("\n---ACCESO DE ADMINISTRADOR---");
            if (administrador.iniciarSesion()) {
                boolean salir = false;
                while (!salir) {
                    System.out.println("\n--- MENÚ DE ADMINISTRADOR ---");
                    System.out.println("1. Volver al Menú Principal");
                    System.out.println("2. Agregar nueva habitación");
                    System.out.println("3. Realizar copia de seguridad (Archivo)");
                    System.out.println("4. Descargar copia de seguridad (Archivo)");
                    System.out.println("5. Agregar nuevo recepcionista");
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

                                hotel.agregarHabitacionDisponible(numHabitacion, precio);

                                break;

                            case 3:
                              // serializar
                                break;

                            case 4:
                                // deserializar archivo json
                                break;

                            case 5:

                                System.out.print("Ingrese el DNI del nuevo recepcionista: ");
                                int dni = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Ingrese el nombre del nuevo recepcionista: ");
                                String nombre = scanner.nextLine();

                                agregarRecepcionista(dni, nombre);
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
              // throw new UsuarioInvalidoEx;
            }
}



         public void menuRecepcionista() /* throws UsuarioInvalidoEx */{
        int dniUsuario = 0;
             System.out.println("Bienvenido, ingrese su dni: ");
             dniUsuario = scanner.nextInt();
             scanner.nextLine();
             Recepcionista recepcionista = buscarRecepcionistaXdni(dniUsuario);
             if (recepcionista != null){
                 System.out.println("\n---ACCESO DE RECEPCIONISTA---");
             if( recepcionista.iniciarSesion()){
                 boolean salir = false;
                 while (!salir) {
                     System.out.println("\n---MENÚ DE RECEPCIONISTA---");
                     System.out.println("1. Volver al menu principal");
                     System.out.println("2. Registrar reserva");
                     System.out.println("3. Realizar check-in de reserva");
                     System.out.println("4. Realizar check-out de habitacion");
                     System.out.println("5. Ver reservas del hotel");
                     System.out.println("6. Ver habitaciones disponibles");
                     System.out.println("7. Ver habitaciones no disponibles");
                     System.out.println("8. Ver habitaciones ocupadas");
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

                                 break;
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
                                 System.out.println(hotel.mostrarReservas());
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
                 //throw new UsuarioInvalidoEx;
             }

             }


         }


        }


