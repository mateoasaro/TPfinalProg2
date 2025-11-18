import org.json.JSONObject;

import java.util.Scanner;

public class Administrador extends Usuario implements metodosUsuario{
    private static final String contraseniaAdministrador = "administracion";
    private Scanner scanner;
    public Administrador(int dniUsuario)
    {
        super(dniUsuario);
        this.scanner = new Scanner(System.in);
    }

    public JSONObject toJson(){
        JSONObject neuvo= new JSONObject();
        neuvo.put("Usuario",super.toJson());

        return neuvo;
    }

    public boolean iniciarSesion(){
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        if (password.equals(contraseniaAdministrador)){
            return true;
        }
        else{
            return false;
        }
    }

}
