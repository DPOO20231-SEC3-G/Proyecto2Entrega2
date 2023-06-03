package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarjeta {

    private String codigo;

    private Date fecha;

    private String verificacion;

    private int saldo;

    public Tarjeta(String nCodigo, String nFecha, String nVerificacion, int nSaldo) throws ParseException {
        codigo = nCodigo;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fecha = formato.parse(nFecha);
        verificacion = nVerificacion;
        saldo = nSaldo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public String getVerificacion() {
        return this.verificacion;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void realizarPago(int costo) {
        saldo -= costo;
    }

    public void realizarReintegro(int costo) {
        saldo += costo;
    }

}
