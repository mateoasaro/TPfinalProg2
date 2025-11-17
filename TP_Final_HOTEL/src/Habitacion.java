import org.json.JSONObject;

import java.util.Objects;

public class Habitacion {
    private int numHabitacion;
    private double precioXnoche;
    private estadoHabitacion estado;

    public Habitacion(int numHabitacion, double precioXnoche ) {
        this.numHabitacion = numHabitacion;
        this.precioXnoche = precioXnoche;
        this.estado = estadoHabitacion.disponible;
    }

    public JSONObject toJson(){
        JSONObject nuevo = new JSONObject();
        nuevo.put("numHabitacion",numHabitacion);
        nuevo.put("precioXnoche",precioXnoche);
        nuevo.put("estadoHabitacion",estado);

        return nuevo;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numHabitacion=" + numHabitacion +
                ", precioXnoche=" + precioXnoche +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return numHabitacion == that.numHabitacion && Double.compare(precioXnoche, that.precioXnoche) == 0 && estado == that.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numHabitacion, precioXnoche, estado);
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public double getPrecioXnoche() {
        return precioXnoche;
    }

    public void setPrecioXnoche(double precioXnoche) {
        this.precioXnoche = precioXnoche;
    }

    public estadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(estadoHabitacion estado) {
        this.estado = estado;
    }
}
