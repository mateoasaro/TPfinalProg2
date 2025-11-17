import java.util.ArrayList;

public class Registro<T> {
    private ArrayList<T> registroT;

    public Registro() {
        this.registroT = new ArrayList<>();
    }

    public void agregarRegistro(T t){
        registroT.add(t);
    }
    public void eliminarRegistro(T t){
        registroT.remove(t);
    }



    public void listar(){
        for (T t: registroT){
            System.out.println(t.toString());
        }
    }

    public ArrayList<T> getRegistroT() {
        return registroT;
    }

    public void setRegistroT(ArrayList<T> registroT) {
        this.registroT = registroT;
    }
}
