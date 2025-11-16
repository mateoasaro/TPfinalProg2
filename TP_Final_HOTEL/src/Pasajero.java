public class Pasajero {
    private int dni;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String domicilio;

    public Pasajero(int dni, String nombre, String apellido, String nacionalidad, String domicilio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", domicilio='" + domicilio + '\'' +
                '}';
    }
}
