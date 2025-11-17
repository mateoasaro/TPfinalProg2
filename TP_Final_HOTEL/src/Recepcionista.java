import java.util.Scanner;

public class Recepcionista extends Usuario implements metodosUsuario{
    private String nombreRecepcionista;
    private static String contraseniaRecepcion = "RECEPCIONISTA123";
    private Scanner scanner;


    public Recepcionista(int dniUsuario, String nombreRecepcionista) {
        super(dniUsuario);
        this.nombreRecepcionista = nombreRecepcionista;
    }
 public boolean iniciarSesion(){
     System.out.print("Ingrese la contraseña: ");
     String password = scanner.nextLine();
     if (password == contraseniaRecepcion){
       return true;
     }
     else{
         return false;
     }
 }

}
