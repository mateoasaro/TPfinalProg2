import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reserva {
    private int contador=0;
    private static int idReserva;
    private Habitacion habitacion;
    private int diasReservados;
    private double precioApagar;
    private boolean checkInRealizado;
    private ArrayList<Pasajero> pasajeros;

    public Reserva( Habitacion habitacion, int diasReservados) {
        this.idReserva = contador++;
        this.habitacion = habitacion;
        this.diasReservados = diasReservados;
        this.precioApagar=(diasReservados*habitacion.getPrecioXnoche());
        this.checkInRealizado = false;
        this.pasajeros = new ArrayList<Pasajero>();
    }

    public JSONObject toJson(){
        JSONObject nuevo= new JSONObject();
        nuevo.put("idReserva",idReserva);
        nuevo.put("habitacion",habitacion.toJson());
        nuevo.put("diasReservados",diasReservados);
        nuevo.put("precioApagar",precioApagar);
        nuevo.put("CheckInRealizado",checkInRealizado);
        JSONArray pasajerosJson=new JSONArray();
        for (Pasajero p:pasajeros){
            pasajerosJson.put(p.toJson());
        }
        nuevo.put("pasajeros",pasajerosJson);

        return nuevo;
    }

    public void listarPasajeros(){
        int i=1;
        for (Pasajero p:pasajeros){
            System.out.println("PASAJERO NUMERO"+i+" DE LA HABITACION "+habitacion.getNumHabitacion()+":"+p.toString());
            i++;
        }
    }

    public boolean existePasajeroXDni(int dni){

        for (Pasajero p:pasajeros){
            if (dni==p.getDniUsuario()){
               return true;
            }
        }
        return false;
    }

    public Pasajero buscarPasajeroXdni(int dni){
        for (Pasajero p : pasajeros){
            if (dni == p.getDniUsuario()){
                return p;

            }

        }
        return null;
    }

    public void agregarPasajero(int dni, String nombre, String apellido, String nacionalidad, String domicilio){
        Pasajero neuvo = new Pasajero(dni, nombre, apellido, nacionalidad, domicilio);

        pasajeros.add(neuvo);
    }



    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", habitacion=" + habitacion +
                ", diasReservados=" + diasReservados +
                ", precioApagar=" + precioApagar +
                ", checkInRealizado=" + checkInRealizado +
                ", pasajeros=" + pasajeros.toString()+
                '}';
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getDiasReservados() {
        return diasReservados;
    }

    public void setDiasReservados(int diasReservados) {
        this.diasReservados = diasReservados;
    }

    public double getPrecioApagar() {
        return precioApagar;
    }

    public void setPrecioApagar(double precioApagar) {
        this.precioApagar = precioApagar;
    }

    public boolean isCheckInRealizado() {
        return checkInRealizado;
    }

    public void setCheckInRealizado(boolean checkInRealizado) {
        this.checkInRealizado = checkInRealizado;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }
}
