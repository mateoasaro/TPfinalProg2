import org.json.JSONObject;

import java.util.Scanner;

public class Recepcionista extends Usuario implements metodosUsuario{
    private String nombreRecepcionista;
    private static String contraseniaRecepcion = "recepcion";
    private Scanner scanner;


    public Recepcionista(int dniUsuario, String nombreRecepcionista) {
        super(dniUsuario);
        this.nombreRecepcionista = nombreRecepcionista;
        this.scanner = new Scanner(System.in);
    }

    public JSONObject toJson(){
        JSONObject neuvo= new JSONObject();
        neuvo.put("Usuario",super.toJson());
        neuvo.put("nombreRecepcionista",nombreRecepcionista);

        return neuvo;
    }

 public boolean iniciarSesion(){
     System.out.print("Ingrese la contraseña: ");
     String password = scanner.nextLine();
     if (password.equals(contraseniaRecepcion)){
       return true;
     }
     else{
         return false;
     }
 }

}
