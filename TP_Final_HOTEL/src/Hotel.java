import java.time.format.ResolverStyle;

public class Hotel {
    private String nombre;
    private Recepcionista recepcionista;
    private double recaudacionTotal;
    private Registro<Habitacion> habitacionesDisponibles;
    private Registro<Habitacion> habitacionesNoDisponibles;
    private Registro<Reserva> registroReservas;

    public Hotel(String nombre, Recepcionista recepcionista) {
        this.nombre = nombre;
        this.recepcionista = recepcionista;
        this.recaudacionTotal=0;
        this.habitacionesDisponibles = new Registro<Habitacion>();
        this.habitacionesNoDisponibles = new Registro<Habitacion>();
        this.registroReservas = new Registro<Reserva>();
    }

    public void listarHabitacionesDisponibles(){
        System.out.println("HABITACIONES  DISPONIBLES: \n");
        habitacionesDisponibles.listar();
    }
    public void listarHabitacionesNoDisponibles(){
        System.out.println("HABITACIONES NO DISPONIBLES: \n");
        habitacionesNoDisponibles.listar();
    }
    public void listarHabitacionesOcupadas(){
        System.out.println("HABITACIONES OCUPADAS Y SUS PASAJEROS");
        for (Reserva r:registroReservas.getRegistroT()){
            if (r.isCheckInRealizado()){
                System.out.println(r.getHabitacion().toString());
            r.listarPasajeros();
            }
        }
    }

    public void realizarCheckIn(int dniPasajero, int numHabitacion){
        Habitacion habitacionReservada=buscarHabitacionPorNumero(numHabitacion);
        Reserva reserva=buscarReservaPorDni(dniPasajero);
        reserva.setCheckInRealizado(true);
        habitacionReservada.setEstado(estadoHabitacion.ocupada);
        setRecaudacionTotal(getRecaudacionTotal()+reserva.getPrecioApagar());
    }

    public void realizarCheckOut(int dniPasajero, int numHabitacion){
        Habitacion habitacionReservada=buscarHabitacionPorNumero(numHabitacion);
        Reserva reserva=buscarReservaPorDni(dniPasajero);

        habitacionReservada.setEstado(estadoHabitacion.disponible);
        habitacionesNoDisponibles.eliminarRegistro(habitacionReservada);
        habitacionesDisponibles.agregarRegistro(habitacionReservada);
        registroReservas.eliminarRegistro(reserva);
    }

    public void agregarHabitacionDisponible(int numHabitacion, double precioXnoche, estadoHabitacion estado){
        Habitacion nueva=new Habitacion(numHabitacion, precioXnoche, estado);

        habitacionesDisponibles.agregarRegistro(nueva);
    }
    public void agregarHabitacionNoDisponible(int numHabitacion, double precioXnoche, estadoHabitacion estado){
        Habitacion nueva=new Habitacion(numHabitacion, precioXnoche, estado);

        habitacionesNoDisponibles.agregarRegistro(nueva);
    }

    public void agregarReserva(int idReserva, int numHabitacion, int diasReservados,int dni, String nombre, String apellido, String nacionalidad, String domicilio){
Habitacion habitacionAreservar=buscarHabitacionPorNumero(numHabitacion);
Reserva nueva= new Reserva(idReserva,habitacionAreservar,diasReservados);
nueva.agregarPasajero(dni, nombre, apellido, nacionalidad, domicilio);
    }

    private Habitacion buscarHabitacionPorNumero(int numHabitacion) {
        for (Habitacion h : habitacionesDisponibles.getRegistroT()) {
            if (h.getNumHabitacion() == numHabitacion) {
                return h;
            }
        }
        for (Habitacion h : habitacionesNoDisponibles.getRegistroT()) {
            if (h.getNumHabitacion() == numHabitacion) {
                return h;
            }
        }
        return null; // No se encontró la habitación
    }
    private Reserva buscarReservaPorDni(int dni){
        for (Reserva r:registroReservas.getRegistroT()){
          if (r.existePasajeroXDni(dni)){
              return r;
          }

        }
        return null;
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(double recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }
}
