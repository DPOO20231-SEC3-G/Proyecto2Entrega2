package MetodosPago;

import java.util.Date;
import java.util.HashMap;

import Modelo.FormasPago;
import Modelo.Tarjeta;

public class PayU extends FormasPago {

    private String nombre = "PayU";

    private String dArchivo = "./Proyecto2Entrega2/data/PayU.txt";

    @Override
    public void guardarMovimiento(String numero, Date vencimiento, String verificacion, int valor, String resultado) {
        // TODO Auto-generated method stub
        super.guardarMovimiento(numero, vencimiento, verificacion, valor, resultado);
    }

    @Override
    public String realizarPago(String numero, Date vencimiento, String verificacion, int valor,
            HashMap<String, Tarjeta> info) {
        // TODO Auto-generated method stub
        return super.realizarPago(numero, vencimiento, verificacion, valor, info);
    }

    public PayU() {
    }

    @Override
    public String getNArchivo() {
        // TODO Auto-generated method stub
        return super.getNArchivo();
    }

    @Override
    public String getNombre() {
        // TODO Auto-generated method stub
        return super.getNombre();
    }

}
