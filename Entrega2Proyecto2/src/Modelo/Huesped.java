package Modelo;
import java.util.ArrayList;

public class Huesped {

    private String nombre;
    private long documento; 
    private String email; 
    private String celular; 
    private boolean necesitaCama; 
    private ArrayList<Reserva> historialReserva;

    public Huesped(String nombre, long documento, String email,String celular,boolean necesitaCama){

        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.celular = celular;
        this.necesitaCama = necesitaCama;

        this.historialReserva = new ArrayList<Reserva>();
    }
    public String getNombre() {
        return nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public boolean isNecesitaCama() {
        return necesitaCama;
    }

    public ArrayList<Reserva> getHistorialReserva() {
        return historialReserva;
    }
    public ArrayList<String> getInfo() {
        return null;
    }


    
    



    
}
