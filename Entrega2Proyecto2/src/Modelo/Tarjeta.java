package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarjeta {

    private String codigo;

    private Date fecha;

    private String verificacion;

    private long saldo;

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

    public long getSaldo() {
        return this.saldo;
    }

    public void realizarPago(long costo) {
        saldo -= costo;
    }

    public void realizarReintegro(long costo) {
        saldo += costo;
    }

}
