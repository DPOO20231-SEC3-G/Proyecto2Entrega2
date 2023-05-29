package Modelo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ControladorHabitaciones {
    private ArrayList<Habitacion> habitaciones;
    private HashMap<String,ArrayList<Tarifa>> tarifasExistentes;
    private HashMap<String,ArrayList<Boolean>> mapTarifas;

    public ControladorHabitaciones(){
        this.mapTarifas = new HashMap<String,ArrayList<Boolean>>();
        mapTarifas.put("estandar", new ArrayList<Boolean>());
        mapTarifas.put("suite", new ArrayList<Boolean>());
        mapTarifas.put("suite doble", new ArrayList<Boolean>());

        this.habitaciones = new ArrayList<Habitacion>();
        this.tarifasExistentes = new HashMap<String,ArrayList<Tarifa>>();
        this.tarifasExistentes.put("estandar", new ArrayList<Tarifa>());
        this.tarifasExistentes.put("suite", new ArrayList<Tarifa>());
        this.tarifasExistentes.put("suite doble", new ArrayList<Tarifa>());
    }
    public void cargarArchivoHabitaciones(File ruta_archivoHabitacion, File ruta_archivoCamas) throws NumberFormatException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(ruta_archivoHabitacion))) {
            String st;
            br.readLine();
            while ((st = br.readLine()) != null) {
                String[] split = st.split(";");
                Habitacion habitacion = new Habitacion(Integer.parseInt(split[0]), split[1], Boolean.parseBoolean(split[2]), 
                    Boolean.parseBoolean(split[3]),Boolean.parseBoolean(split[4]),split[5],Float.parseFloat(split[6]),Boolean.parseBoolean(split[7]),
                    Boolean.parseBoolean(split[8]),Boolean.parseBoolean(split[9]),Boolean.parseBoolean(split[10]),Boolean.parseBoolean(split[11]),
                    Boolean.parseBoolean(split[12]),Boolean.parseBoolean(split[13]),Boolean.parseBoolean(split[14]), Integer.parseInt(split[15]),
                    Boolean.parseBoolean(split[16]),Boolean.parseBoolean(split[17]),Boolean.parseBoolean(split[18]));
                this.habitaciones.add(habitacion);}}
        try (BufferedReader br = new BufferedReader(new FileReader(ruta_archivoCamas))) {
            String st;
            br.readLine();
            while ((st = br.readLine()) != null) {
                String[] split = st.split(";");
                int id = Integer.parseInt(split[0]);
                Cama cama = new Cama(split[1],Integer.parseInt(split[2]),Boolean.parseBoolean(split[3]));
                this.habitaciones.get(id-1).addCama(cama);}}
    }
    public void crearHabitacion(String ubicacion, boolean balcon, boolean vista, boolean cocinaIntegrada,
        String tipoHabitacion, ArrayList<ArrayList<String>> infoCamas,float tamañoMts, boolean aire,
        boolean calefaccion, boolean tv, boolean cafetera, boolean ropaCama, boolean tapetes, boolean plancha,
        boolean secador, int voltaje, boolean usbA, boolean usbC, boolean desayuno) {

            int id = (habitaciones.size()+1);
            Habitacion habitacion = new Habitacion(id, ubicacion, balcon, vista, cocinaIntegrada, tipoHabitacion, 
            tamañoMts, aire,calefaccion,tv,cafetera,ropaCama,tapetes,plancha,secador,voltaje,usbA,usbC,desayuno);
            for(int i=0; i<infoCamas.size();i++){
                ArrayList<String> info = infoCamas.get(i);
                Cama cama = new Cama(info.get(0),Integer.parseInt(info.get(1)),Boolean.parseBoolean(info.get(2)));
                habitacion.addCama(cama);
            }
            for(int i=0;i< habitacion.getCamas().size();i++){
                Cama cama = habitacion.getCamas().get(i);
                if(i==0){
                try {
                    Files.write(Paths.get("./Entrega2Proyecto2/Datos/Camas.txt"),("\n"+id+";"+cama.getTamaño()+";"+cama.getCantidadPersonas()+";"+cama.isSoloNiños()).getBytes(), StandardOpenOption.APPEND );
                } catch (IOException e) {
                    e.printStackTrace();
                }}
                else if(i == habitacion.getCamas().size()-1){
                    try {
                        Files.write(Paths.get("./Entrega2Proyecto2/Datos/Camas.txt"),("\n"+id+";"+cama.getTamaño()+";"+cama.getCantidadPersonas()+";"+cama.isSoloNiños()).getBytes(), StandardOpenOption.APPEND );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        Files.write(Paths.get("./Entrega2Proyecto2/Datos/Camas.txt"),("\n"+id+";"+cama.getTamaño()+";"+cama.getCantidadPersonas()+";"+cama.isSoloNiños()).getBytes(), StandardOpenOption.APPEND );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.habitaciones.add(habitacion);
            try {
                Files.write(Paths.get("./Entrega2Proyecto2/Datos/Habitaciones.txt"),("\n"+id+";"+habitacion.getUbicacion()+";"+habitacion.isBalcon()+";"+habitacion.isVista()+";"+habitacion.isCocinaIntegrada()+";"+habitacion.getTipoHabitacion()+";"+habitacion.getTamañoMts()+";"+habitacion.isAire()+";"+habitacion.isCalefaccion()+";"+habitacion.isTv()+";"+habitacion.isCafetera()+";"+habitacion.isRopaCama()+";"+habitacion.isTapetes()+";"+habitacion.isPlancha()+";"+habitacion.isSecador()+";"+habitacion.getVoltaje()+";"+habitacion.isUsbA()+";"+habitacion.isUsbC()+";"+habitacion.isDesayuno()).getBytes(), StandardOpenOption.APPEND );
            } catch (IOException e) {
                
                e.printStackTrace();
            }
            
    }
    public void cargarTarifaServicio(String tipoHabitacion, double valorTarifa, String fechaInicial, String fechaFinal,
            String dias) throws ParseException {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateInicial = sdf.parse(fechaInicial);
                Date dateFinal = sdf.parse(fechaFinal);
                Tarifa tarifa = new Tarifa(dias, valorTarifa, tipoHabitacion, dateInicial, dateFinal);
                this.tarifasExistentes.get(tipoHabitacion).add(tarifa);   
                try {
                    Files.write(Paths.get("./Entrega2Proyecto2/Datos/Tarifas.txt"),("\n"+dias+";"+valorTarifa+";"+tipoHabitacion+";"+fechaInicial+";"+fechaFinal).getBytes(), StandardOpenOption.APPEND );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
    }
    public String numDayToString(int numero){
        String String = "";
        if(numero == 1){String = "D";}
        else if(numero == 2){String = "L";}
        else if(numero == 3){String = "M";}
        else if(numero == 4){String = "X";}
        else if(numero == 5){String = "J";}
        else if(numero == 6){String = "V";}
        else if(numero == 7){String = "S";}
        return String;
    }
    public String tarifasSinDefinirProximoAño(){
        String[] keys = {"estandar","suite","suite doble"};
        HashMap<String, ArrayList<String>> tarifasSinDefinir = new HashMap<String, ArrayList<String>>();
        tarifasSinDefinir.put("estandar", new ArrayList<String>());
        tarifasSinDefinir.put("suite", new ArrayList<String>());
        tarifasSinDefinir.put("suite doble", new ArrayList<String>());
        boolean algunaAplica = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE,0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);
        hoy.set(Calendar.DAY_OF_MONTH,1);
        for(int i=0; i<375;i++){
            String diaSemana = numDayToString(hoy.get(Calendar.DAY_OF_WEEK));
            for(int key = 0; key<keys.length;key++){
                algunaAplica  = false;
                for(int pos = 0; pos< tarifasExistentes.get(keys[key]).size();pos++){
                    boolean enRango = tarifasExistentes.get(keys[key]).get(pos).getRangoFechas().fechaEnRango(hoy.getTime()); 
                    boolean aplicaDia = tarifasExistentes.get(keys[key]).get(pos).tarifaAplicaDia(diaSemana);
                    if(enRango == true && aplicaDia == true){
                            algunaAplica = true;}}
            if(algunaAplica == false){
                tarifasSinDefinir.get(keys[key]).add(" "+diaSemana +":"+ sdf.format(hoy.getTime()));}
                mapTarifas.get(keys[key]).add(algunaAplica);
            }
            
            hoy.add(Calendar.DAY_OF_YEAR, 1);}
        
        String retorno = "";
        for(int key = 0; key<keys.length;key++){
            retorno += keys[key] + ":";
            for(int pos = 0; pos<tarifasSinDefinir.get(keys[key]).size();pos++){
                retorno += tarifasSinDefinir.get(keys[key]).get(pos);
            }
            retorno += ".\n";
        }

        return retorno;
    }
    public Habitacion getHabitacion(int id){
        return this.habitaciones.get(id-1);
    }

    public String consultarInventario(){
        String retorno = "";
        for(int i=0;i<habitaciones.size();i++){
            retorno += habitaciones.get(i).textoInventario() + "\n";
        }

        return retorno;
    }
    public void cargarTarifas() throws FileNotFoundException, IOException, ParseException{
        try (BufferedReader br = new BufferedReader(new FileReader("./Entrega2Proyecto2/Datos/Tarifas.txt"))) {
            String st;
            br.readLine();
            while ((st = br.readLine()) != null) {
                String[] split = st.split(";");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateInicial = sdf.parse(split[3]);
                Date dateFinal = sdf.parse(split[4]);
                Tarifa tarifa = new Tarifa(split[0], Double.parseDouble(split[1]), split[2], dateInicial, dateFinal);
                tarifasExistentes.get(split[2]).add(tarifa);}}
    }
    public double getPrecioDia(Habitacion habitacion, Date fecha){
        double min = 10e10;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        String diaSemana = numDayToString(calendar.get(Calendar.DAY_OF_WEEK));
        for(int i=0;i<tarifasExistentes.get(habitacion.getTipoHabitacion()).size();i++){
            Tarifa tarifa = tarifasExistentes.get(habitacion.getTipoHabitacion()).get(i);
            if(tarifa.tarifaAplicaDia(diaSemana) && tarifa.getRangoFechas().fechaEnRango(fecha) && (tarifa.getValorTarifa() < min)){
                min = tarifa.getValorTarifa();
            }
        }
        if(min >= 10e10){
            min = 0;
        }
        return min;
    }
    public double getPrecioHabitacion(Habitacion habitacion, RangoFechas rangoFechas){
        double precio = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( rangoFechas.getFechaInicial());
        while(rangoFechas.fechaEnRango(calendar.getTime()) == true){
            precio += getPrecioDia(habitacion, calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1); 
        }

        return precio;
    }
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }
    public HashMap<String, ArrayList<Tarifa>> getTarifasExistentes() {
        return tarifasExistentes;
    }
    public HashMap<String, ArrayList<Boolean>> getMapTarifas() {
        tarifasSinDefinirProximoAño();
        return mapTarifas;
    }
   
    public ArrayList<Color> getListOcupacion(){
        ArrayList<Color> listaColores = new ArrayList<Color>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH,1);

        int totalHabs = habitaciones.size();

        int i = 0;
        while(i < 375){
        int habitacionesOcupadas = 0;
        for(Habitacion habitacion : habitaciones){
            Boolean ocupada = false;
            for(Reserva reserva : habitacion.getReservas()){
                if(reserva.getFechas().fechaEnRango(cal.getTime())){
                    ocupada = true;
                }

            }
            if(ocupada){
                habitacionesOcupadas++;
            }
            
        }
        cal.add(Calendar.DAY_OF_YEAR, 1);

        float ocupacion = (float) habitacionesOcupadas / totalHabs;
        
        if(ocupacion < 0.25){
            listaColores.add(Color.decode("#EFFBEF"));
        }
        else if(ocupacion >= 0.25 && ocupacion < 0.5){
            listaColores.add(Color.decode("#A9F5A9"));
        }
        else if(ocupacion >= 0.5 && ocupacion < 0.75){
            listaColores.add(Color.decode("#00FF00"));
        }
        else if(ocupacion >= 0.75 && ocupacion <= 1){
            listaColores.add(Color.decode("#298A08"));
        }
        i++;
    }


        return listaColores;
    }
    
    
}
