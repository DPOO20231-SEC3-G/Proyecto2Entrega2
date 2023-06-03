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

        BufferedReader br = new BufferedReader(new FileReader("./Entrega2Proyacto2/Datos/MetodosPagoActivos.txt"));

        String linea = br.readLine();

        while (linea != null) {

            Class<?> miClase = Class.forName("MetodosPago." + linea);
            Constructor<?> miConstructor = miClase.getConstructor();
            Object miInstancia = miConstructor.newInstance();

            if (miInstancia instanceof FormasPago) {
                formasDePago.add((FormasPago) miInstancia);
            }

            linea = br.readLine();

        }

    }

    public ControladorPagos() {

    }

    public ArrayList<FormasPago> getFormasDePago() {
        return this.formasDePago;
    }

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
        ControladorPagos controlador = new ControladorPagos();
        controlador.cargarFormasPago();
        boolean continuar = true;
        while (continuar == true) {
            for (int i = 0; i < controlador.getFormasDePago().size(); i++) {
                System.out.println(i + "." + controlador.getFormasDePago().get(i).getNombre());
                i++;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String cadena = reader.readLine();
            System.out.print("Ingrese una opcion:");
            if (cadena == "exit") {
                continuar = false;
            } else {
                System.out.println(controlador.getFormasDePago().get(Integer.parseInt(cadena)).getNArchivo());
            }

        }

    }

}
