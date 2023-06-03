package Modelo;

import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public abstract class FormasPago {

    private String nombre;

    private String dArchivo;

    public FormasPago() {
    }

    public String realizarPago(String numero, Date vencimiento, String verificacion, int valor,
            HashMap<String, Tarjeta> info) {
        String rta;

        if (info.containsKey(numero)) {
            Tarjeta tarjeta = info.get(numero);
            if (vencimiento.after(new Date())) {
                if (vencimiento.equals(tarjeta.getFecha())) {
                    if (verificacion.equals(tarjeta.getVerificacion())) {
                        if (valor <= tarjeta.getSaldo()) {
                            rta = "El pago se ha realizado con exito";
                        } else {
                            rta = "Saldo insuficiente";
                        }
                    } else {
                        rta = "El codigo de verificacion no corresponde al de la tarjeta";
                    }
                } else {
                    rta = "La fecha no corresponde a la de la tarjeta";
                }
            } else {
                rta = "La fecha ingresada de vencimiento ingresada ya venció";
            }
        } else {
            rta = "La tarjeta no se encuentra en la base de datos (Verifique su numero)";
        }
        guardarMovimiento(numero, vencimiento, verificacion, valor, rta);

        return rta;

    }

    public void guardarMovimiento(String numero, Date vencimiento, String verificacion, int valor, String resultado) {
        String aImprimir = "Transicion " + new Date().toString() + " :\nNumero de tarjeta: " + numero
                + "\nFecha de vencimiento: " + vencimiento.toString() + "\nCosto de transaccion: -" + valor + "\n"
                + resultado;
        try (FileWriter escritor = new FileWriter(dArchivo, true)) {
            escritor.write(aImprimir + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNArchivo() {
        return this.nombre;
    }

}
