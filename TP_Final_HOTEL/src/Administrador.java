import java.util.Scanner;

public class Administrador extends Usuario implements metodosUsuario{
    private static final String contraseniaAdministrador = "ADMINISTRADOR123";
    private Scanner scanner;
    public Administrador(String dniUsuario)
    {
        super(dniUsuario);
    }
    public boolean iniciarSesion(){
        System.out.print("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        if (password == contraseniaAdministrador){
            return true;
        }
        else{
            return false;
        }
    }

}
