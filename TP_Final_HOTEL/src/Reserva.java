import java.util.ArrayList;

public class Reserva {
    private int idReserva;
    private Habitacion habitacion;
    private int diasReservados;
    private double precioApagar;
    private boolean checkInRealizado;
    private ArrayList<Pasajero> pasajeros;

    public Reserva(int idReserva, Habitacion habitacion, int diasReservados, boolean checkInRealizado) {
        this.idReserva = idReserva;
        this.habitacion = habitacion;
        this.diasReservados = diasReservados;
        this.precioApagar=(diasReservados*habitacion.getPrecioXnoche());
        this.checkInRealizado = checkInRealizado;
        this.pasajeros = new ArrayList<Pasajero>();
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
