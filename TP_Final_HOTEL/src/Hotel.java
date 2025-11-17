import org.json.JSONArray;
import org.json.JSONObject;

import java.time.format.ResolverStyle;
import java.util.ArrayList;

public class Hotel {
    private String nombre;
    private double recaudacionTotal;
    private Registro<Habitacion> habitacionesDisponibles;
    private Registro<Habitacion> habitacionesNoDisponibles;
    private Registro<Reserva> registroReservas;


    public Hotel(String nombre) {
        this.nombre = nombre;

        this.recaudacionTotal=0;
        this.habitacionesDisponibles = new Registro<Habitacion>();
        this.habitacionesNoDisponibles = new Registro<Habitacion>();
        this.registroReservas = new Registro<Reserva>();
    }

    public JSONObject toJson(){
        JSONObject nuevo = new JSONObject();
        nuevo.put("nombre",nombre);
        nuevo.put("recaudacionTotal",recaudacionTotal);
        JSONArray habitacionesDisponiblesJson = new JSONArray();
        JSONArray habitacionesNoDisponiblesJson = new JSONArray();
        JSONArray reservasJson = new JSONArray();
        for (Habitacion h : habitacionesDisponibles.getRegistroT()){
            habitacionesDisponiblesJson.put(h.toJson());
        }
        for (Habitacion h : habitacionesNoDisponibles.getRegistroT()){
            habitacionesNoDisponiblesJson.put(h.toJson());
        }
        for (Reserva r : registroReservas.getRegistroT()){
            habitacionesNoDisponiblesJson.put(r.toJson());
        }
        nuevo.put("habitacionesDisponibles",habitacionesDisponiblesJson);
        nuevo.put("habitacionesNoDisponibles",habitacionesNoDisponiblesJson);
        nuevo.put("registroReservas",reservasJson);

        return nuevo;
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

    public void agregarHabitacionDisponible(int numHabitacion, double precioXnoche){
        Habitacion nueva=new Habitacion(numHabitacion, precioXnoche);

        habitacionesDisponibles.agregarRegistro(nueva);
    }
    public void agregarHabitacionNoDisponible(int numHabitacion, double precioXnoche){
        Habitacion nueva=new Habitacion(numHabitacion, precioXnoche);

        habitacionesNoDisponibles.agregarRegistro(nueva);
    }

    public void agregarReserva( int numHabitacion, int diasReservados,int dni, String nombre, String apellido, String nacionalidad, String domicilio){
Habitacion habitacionAreservar=buscarHabitacionPorNumero(numHabitacion);
Reserva nueva= new Reserva(habitacionAreservar,diasReservados);
nueva.agregarPasajero(dni, nombre, apellido, nacionalidad, domicilio);
    }

    public String mostrarReservas(){
        String info ="";
        for (Reserva r:registroReservas.getRegistroT()){
            info += r.toString();
        }
        return info;
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
        return null;
    }
    public Reserva buscarReservaPorDni(int dni){
        for (Reserva r:registroReservas.getRegistroT()){
          if (r.existePasajeroXDni(dni)){
              return r;
          }

        }
        return null;
    }

    public String mostrarReservasXdni(int dni){
        String info = "";
        for (Reserva r : registroReservas.getRegistroT()){
            info += r.toString();

        }
        return info;
    }

    public boolean existePasajeroXdni(int dni){
        for (Reserva r: registroReservas.getRegistroT()){
            if (r.existePasajeroXDni(dni)){
                return true;
            }
        }
        return false;
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }


    public void setRecaudacionTotal(double recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }


}
