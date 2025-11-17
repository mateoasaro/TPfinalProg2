import org.json.JSONObject;

import java.util.Objects;

public abstract class Usuario {
    private int dniUsuario;

    public Usuario(int dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public JSONObject toJson(){
        JSONObject nuevo = new JSONObject();
        nuevo.put("dniUsuario",dniUsuario);
        return nuevo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dniUsuario=" + dniUsuario +
                '}';
    }

    public int getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(int dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dniUsuario == usuario.dniUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dniUsuario);
    }
}
