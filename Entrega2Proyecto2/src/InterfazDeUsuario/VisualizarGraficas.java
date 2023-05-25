package InterfazDeUsuario;

import javax.swing.JDialog;
//import javax.swing.void;


import Modelo.*;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.knowm.xchart.*;

public class VisualizarGraficas extends JDialog{
	
	public static void main(String[] args){

		System.out.println("Hello, World!");
		

	}
    
    public VisualizarGraficas(){

        setTitle("Gráficas");
        setSize(400, 500);

        setLayout(new GridLayout(1,5));
    }

    public void graficaProductosVendidos(Hotel hotel){
        
        ArrayList<Servicio> servicios = hotel.getControladorServicios().getServicios();
        ArrayList<ProductoRestaurante> productoRestaurantes = hotel.getControladorServicios().getMenu();

        ArrayList<String> nombres = new ArrayList<String>();
        ArrayList<Integer> vendidos = new ArrayList<Integer>();

        for(Servicio servicio : servicios){
            nombres.add(servicio.getNombreServicio());
            vendidos.add(servicio.getVentas());
        }
        for(ProductoRestaurante productoRestaurante:productoRestaurantes){
            nombres.add(productoRestaurante.getNombreServicio());
            vendidos.add(productoRestaurante.getVentas());
        }

   
    }

    public void graficarValorFacturas(Hotel hotel){

        ArrayList<Reserva> reservas = hotel.getControladorReservas().getReservas();

        ArrayList<Date> fechas = new ArrayList<Date>();
        

        HashMap<Date,ArrayList<Long>> hashMap = new HashMap<Date,ArrayList<Long>>();

        for(Reserva reserva:reservas){

            if(hashMap.containsKey(reserva.getFechas().getFechaFinal()) == false){
                hashMap.put(reserva.getFechas().getFechaFinal(), new ArrayList<Long>());
            }

            hashMap.get(reserva.getFechas().getFechaFinal()).add(hotel.getControladorReservas().precioReserva(reserva, hotel.getControladorHabitaciones()));

        }

        ArrayList<Long> valor = promedios(hashMap);
        fechas.addAll(hashMap.keySet());

        valor.size();


   
    }

    private ArrayList<Long> promedios(HashMap<Date,ArrayList<Long>> hashMap){

        ArrayList<Long> valor = new ArrayList<Long>();

        for(Date key : hashMap.keySet()){
            ArrayList<Long> longs = hashMap.get(key);
            Long sum = Long.valueOf(0);
            for(Long long1:longs){
                sum += long1;
            }
            valor.add(sum/longs.size());
        }
            return valor;
    }

    public void graficarConsumoTarifa(Hotel hotel){

        ArrayList<Long> consumos = new ArrayList<Long>();
        ArrayList<Long> tarifa = new ArrayList<Long>();

        for(Reserva reserva : hotel.getControladorReservas().getReservas()){

            consumos.add(hotel.getControladorReservas().consumoRestaurante(reserva));

            tarifa.add((long) hotel.getControladorHabitaciones().getPrecioHabitacion(reserva.getHabitacion(), reserva.getFechas()));

        }

 

    }

    public void graficar5HabitacionesMasDemandadas(Hotel hotel){

        ArrayList<Habitacion> habitaciones = hotel.getControladorHabitaciones().getHabitaciones();
        habitaciones.sort(new Comparator<Habitacion>() {
            @Override
            public int compare(Habitacion h1, Habitacion h2){
                return Integer.compare(h1.getReservas().size(), h2.getReservas().size());
            }
        });

        if(habitaciones.size() > 5){

            habitaciones = new ArrayList<>(habitaciones.subList(0, 5));

        }


 

    }


    public void graficarHuespedesConMasReservas(Hotel hotel){

        ArrayList<Huesped> huespedes = hotel.getControladorHuespedes().getHuespedes();

        huespedes.sort(new Comparator<Huesped>() {
            @Override
            public int compare(Huesped h1, Huesped h2){
                return Integer.compare(hotel.getControladorReservas().getReservaDocumento(h1.getDocumento()).size(), hotel.getControladorReservas().getReservaDocumento(h2.getDocumento()).size());
            }
        });

        
    }
        

    
}
