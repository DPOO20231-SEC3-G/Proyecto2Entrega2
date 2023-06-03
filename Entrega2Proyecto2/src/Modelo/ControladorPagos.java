package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControladorPagos {

    private HashMap<String, Tarjeta> info = new HashMap<String, Tarjeta>();

    private ArrayList<FormasPago> formasDePago = new ArrayList<FormasPago>();

    public void cargarFormasPago()
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

        BufferedReader br = new BufferedReader(new FileReader("./Entrega2Proyecto2/Datos/MetodosPagoActivos.txt"));

        String linea = br.readLine();

        while (linea != null) {

            Class<?> miClase = Class.forName("MetodosPago."+linea);
            Constructor<?> miConstructor = miClase.getDeclaredConstructor(String.class);
            Object miInstancia = miConstructor.newInstance(linea);

            if (miInstancia instanceof FormasPago) {
                formasDePago.add((FormasPago) miInstancia);
            }

            linea = br.readLine();

        }
        
        br.close();

    }

    public ControladorPagos() {

    }

    public ArrayList<FormasPago> getFormasDePago() {
        return this.formasDePago;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ControladorPagos controlador = new ControladorPagos();
        controlador.cargarFormasPago();
        boolean continuar = true;
        while (continuar == true) {
            for (int i = 0; i < controlador.getFormasDePago().size(); i++) {
                System.out.println(i + "." + controlador.getFormasDePago().get(i).getNombre());
                i++;
            }
            System.out.print("Ingrese una opcion:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String cadena = reader.readLine();
            if (cadena.equals("exit")) {
                continuar = false;
            } else {
                System.out.println(controlador.getFormasDePago().get(Integer.parseInt(cadena)).getNArchivo());
            }

        }

    }

}
