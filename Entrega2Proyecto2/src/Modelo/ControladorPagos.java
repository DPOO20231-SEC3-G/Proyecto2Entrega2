package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

public class ControladorPagos {

    private HashMap<String, Tarjeta> info = new HashMap<String, Tarjeta>();

    private ArrayList<FormasPago> formasDePago = new ArrayList<FormasPago>();

    public void cargarFormasPago()
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    	
    	formasDePago = new ArrayList<FormasPago>();
    	
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
    
    public void cargarInfoTarjetas() throws IOException, NumberFormatException, ParseException {
    
    BufferedReader br = new BufferedReader(new FileReader("./Entrega2Proyecto2/Datos/Tarjetas.txt"));
    
    String linea = br.readLine();
    
    while (linea!= null) {
    	
    	String[] elementos = linea.split(";");
    	String id = elementos[0];
    	String fecha = elementos[1];
    	String verificacion = elementos[2];
    	int saldo = Integer.parseInt(elementos[3]);
    	Tarjeta tarjeta = new Tarjeta(id,fecha,verificacion,saldo);
    	info.put(id, tarjeta);
    	linea = br.readLine();
    	}
    br.close();
    }
    
    public void guardarInfoTarjetas() throws IOException {
    	BufferedWriter writer = new BufferedWriter(new FileWriter("./Entrega2Proyecto2/Datos/Tarjetas.txt"));
    	for (Entry<String, Tarjeta> entry : this.getInfo().entrySet()) {
    		Tarjeta tarjeta = entry.getValue();
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		String fechaString = formatter.format(tarjeta.getFecha());
    		String texto = tarjeta.getCodigo()+";"+fechaString+";"+tarjeta.getVerificacion()+";"+String.valueOf(tarjeta.getSaldo());
    		writer.write(texto);
    		writer.newLine();
    	}
    	writer.close();
    }

    public ControladorPagos() {

    }

    public ArrayList<FormasPago> getFormasDePago() {
        return this.formasDePago;
    }
    
    public HashMap<String,Tarjeta> getInfo(){
    	return this.info;
    }
}
