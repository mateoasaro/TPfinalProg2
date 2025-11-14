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
        this.pasajeros = new ArrayList<>();
    }
}
