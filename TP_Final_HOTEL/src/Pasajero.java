import java.util.Objects;

public class Pasajero extends Usuario{
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String domicilio;

    public Pasajero(int dniUsuario, String nombre, String apellido, String nacionalidad, String domicilio) {
        super(dniUsuario);
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                super.toString() +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", domicilio='" + domicilio + '\'' +
                '}';
    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
