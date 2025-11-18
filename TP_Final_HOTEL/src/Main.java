//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
Hotel hotel = new Hotel("UTN");
try {
    hotel.agregarHabitacionDisponible(11, 15);
    hotel.agregarHabitacionDisponible(12, 35);
    hotel.agregarHabitacionDisponible(13, 50);
    hotel.agregarHabitacionDisponible(14, 15);
    hotel.agregarHabitacionDisponible(15, 35);
    hotel.agregarHabitacionDisponible(16, 50);
    hotel.agregarHabitacionDisponible(17, 15);
    hotel.agregarHabitacionDisponible(18, 35);
    hotel.agregarHabitacionDisponible(19, 50);
}catch (NumHabitacionRepetidoEx e){

}
        hotel.agregarReserva(19,3,500,"homero","capozzo","argentina","mar del plata");

Administrador admin = new Administrador(123);
Sistema sistemaHotel = new Sistema(hotel,admin);

try {
    sistemaHotel.agregarRecepcionista(222, "recepcionista1");
    sistemaHotel.agregarRecepcionista(987, "recepcionista 2");
}catch (DniRepetidoException e){

}
        sistemaHotel.menuPrincipal();


    }
}