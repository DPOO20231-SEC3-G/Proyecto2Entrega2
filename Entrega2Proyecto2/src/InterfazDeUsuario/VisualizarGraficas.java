package InterfazDeUsuario;

import javax.swing.JButton;
import javax.swing.JDialog;
//import javax.swing.void;
import javax.swing.WindowConstants;

import Modelo.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

public class VisualizarGraficas extends JDialog implements ActionListener{

    private Hotel hotel;

    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    
    public Hotel getHotel() {
        return hotel;
    }

    public VisualizarGraficas(Hotel hotel){

        this.hotel  = hotel;

        setTitle("Gráficas");
        setSize(400, 500);

        setLayout(new GridLayout(5,1));

        boton1 = new JButton("Gráfica ventas servicios");  boton1.addActionListener(this);
        boton2 = new JButton("Gráfica valor facturas");  boton2.addActionListener(this);
        boton3 = new JButton("Gráfica relacion consumo/tarifas");  boton3.addActionListener(this);
        boton4 = new JButton("Gráfica 5 Habitaciones mas solicitadas");  boton4.addActionListener(this);
        boton5 = new JButton("Gráficar huespedes con mas reservas");  boton5.addActionListener(this);

        add(boton1);
        add(boton2);
        add(boton3);
        add(boton4);
        add(boton5);


        setVisible(true);
    }

    public void graficaProductosVendidos(Hotel hotel){
        
        ArrayList<Servicio> servicios = hotel.getControladorServicios().getServicios();
        ArrayList<ProductoRestaurante> productoRestaurantes = hotel.getControladorServicios().getMenu();

        ArrayList<ArrayList<String>> nombres = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<Integer>> vendidos = new ArrayList<ArrayList<Integer>>();

        for(Servicio servicio : servicios){
            ArrayList<String> nombre1 = new ArrayList<String>(); nombre1.add(servicio.getNombreServicio());
            ArrayList<Integer> vendido = new ArrayList<Integer>(); vendido.add(servicio.getVentas());
            nombres.add(nombre1);
            vendidos.add(vendido);
        }
        for(ProductoRestaurante productoRestaurante:productoRestaurantes){
            ArrayList<String> nombre1 = new ArrayList<String>(); nombre1.add(productoRestaurante.getNombreServicio());
            ArrayList<Integer> vendido = new ArrayList<Integer>(); vendido.add(productoRestaurante.getVentas());
            nombres.add(nombre1);
            vendidos.add(vendido);
        }

        CategoryChart categoryChart = new CategoryChartBuilder().build();

        categoryChart.setYAxisTitle("Unidades vendidas");

        for(int i = 0; i < nombres.size(); i++){
            ArrayList<String> nombre = nombres.get(i);
            ArrayList<String> i_list = new ArrayList<String>(); i_list.add("-");
            categoryChart.addSeries(nombre.get(0), i_list, vendidos.get(i));
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new SwingWrapper<>(categoryChart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
                
            }
        });

        thread.start();

   
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

        XYChart xyChart = new XYChartBuilder().build();

        xyChart.addSeries("Hola", fechas,valor);
        xyChart.getStyler().setLegendVisible(false);
        xyChart.setYAxisTitle("Precio factura (COP)");
        xyChart.setXAxisTitle("Fecha de reserva");

        xyChart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new SwingWrapper<>(xyChart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
                        
                    }
                });

        thread.start();



   
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
            System.out.println(hotel.getControladorHabitaciones().getPrecioHabitacion(reserva.getHabitacion(), reserva.getFechas()));
        }

        XYChart xyChart = new XYChartBuilder().build();

        xyChart.addSeries("Tarifas", tarifa,consumos);

        xyChart.setYAxisTitle("Consumo en el hotel (COP)");
        xyChart.setXAxisTitle("Precio pagado por habitacion");
        xyChart.getStyler().setLegendVisible(false);
        xyChart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new SwingWrapper<>(xyChart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
                        
                    }
                });

        thread.start();

    }

    public void graficar5HabitacionesMasDemandadas(Hotel hotel){

        ArrayList<Habitacion> habitaciones = hotel.getControladorHabitaciones().getHabitaciones();
        habitaciones.sort(new Comparator<Habitacion>() {
            @Override
            public int compare(Habitacion h1, Habitacion h2){
                return Integer.compare(h1.getReservas().size(), h2.getReservas().size());
            }
        });
        Collections.reverse(habitaciones);
        if(habitaciones.size() > 5){
            
            habitaciones = new ArrayList<>(habitaciones.subList(0, 5));

        }

        ArrayList<Integer> numReservas = new ArrayList<Integer>();
        ArrayList<String> habs = new ArrayList<String>();

        for(Habitacion habitacion: habitaciones){
            numReservas.add(habitacion.getReservas().size());
            habs.add(habitacion.getUbicacion());
        }

        CategoryChart categoryChart = new CategoryChartBuilder().build();
        categoryChart.getStyler().setLegendVisible(false);
        categoryChart.setYAxisTitle("Numero de reservas");
        categoryChart.setXAxisTitle("Habitaciones");

        categoryChart.addSeries("Habitaciones", habs, numReservas);
        

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new SwingWrapper<>(categoryChart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
                
            }
        });

        thread.start();
        

    }


    public void graficarHuespedesConMasReservas(Hotel hotel){

        ArrayList<Huesped> huespedes = hotel.getControladorHuespedes().getHuespedes();

        huespedes.sort(new Comparator<Huesped>() {
            @Override
            public int compare(Huesped h1, Huesped h2){
                return Integer.compare(hotel.getControladorReservas().getReservaDocumento(h1.getDocumento()).size(), hotel.getControladorReservas().getReservaDocumento(h2.getDocumento()).size());
            }
        });
        Collections.reverse(huespedes);

        if(huespedes.size() > 10){
            
            huespedes = new ArrayList<>(huespedes.subList(0, 10));

        }

        ArrayList<Integer> numReservas = new ArrayList<Integer>();
        ArrayList<String> nombre = new ArrayList<String>();
        for(Huesped huesped: huespedes){
            numReservas.add(hotel.getControladorReservas().getReservaDocumento(huesped.getDocumento()).size());
            nombre.add(huesped.getNombre());
        }

        CategoryChart categoryChart = new CategoryChartBuilder().build();
        categoryChart.getStyler().setLegendVisible(false);
        categoryChart.setYAxisTitle("Numero de reservas");
        categoryChart.setXAxisTitle("Nombre del huesped");

        categoryChart.addSeries("Huespedes", nombre, numReservas);
        

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new SwingWrapper<>(categoryChart).displayChart().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
                
            }
        });

        thread.start();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1){
            graficaProductosVendidos(hotel);
        }
        else if(e.getSource() == boton2){
            graficarValorFacturas(hotel);
        }
        else if(e.getSource() == boton3){
            graficarConsumoTarifa(hotel);
        }
        else if(e.getSource() == boton4){
            graficar5HabitacionesMasDemandadas(hotel);
        }
        else if(e.getSource() == boton5){
            graficarHuespedesConMasReservas(hotel);
        }
    }
        

    
}
