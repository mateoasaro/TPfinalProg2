public class Hotel {
    private String nombre;
    private Recepcionista recepcionista;
    private Registro<Habitacion> habitacionesDisponibles;
    private Registro<Habitacion> habitacionesNoDisponibles;
    private Registro<Reserva> registroReservas;

    public Hotel(String nombre, Recepcionista recepcionista) {
        this.nombre = nombre;
        this.recepcionista = recepcionista;
        this.habitacionesDisponibles = new Registro<Habitacion>();
        this.habitacionesNoDisponibles = new Registro<Habitacion>();
        this.registroReservas = new Registro<Reserva>();
    }
}
