package InterfazDeUsuario;

import javax.swing.*;

import Modelo.Hotel;
import Modelo.Usuario;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class InterfazAdministrador extends JFrame implements ActionListener {
    private JButton botonCrearHabitacion;
    private JButton botonCargarTarifaHabitacion;
    private JButton botonCrearServicio;
    private JButton botonModificarTarifaServicio;
    private JButton botonCrearProductoRestaurante;
    private JButton botonConsultarTarifas;
    private JButton botonVerDiagramaOcupacion;
    private JButton cambiarUsuario;

    private Hotel hotel;

    public InterfazAdministrador(Hotel hotel, Usuario user) {

        this.hotel = hotel;

        setTitle("Bienvenid@: " +user.getRol() + "  "+ user.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel panel = new JPanel(new GridLayout(2, 4, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        

        botonCrearHabitacion = new JButton("Crear Habitacion para inventario");
        botonCrearHabitacion.addActionListener(this);
        botonCargarTarifaHabitacion = new JButton("Cargar tarifa de habitación");
        botonCargarTarifaHabitacion.addActionListener(this);
        botonCrearServicio = new JButton("Crear servicio");
        botonCrearServicio.addActionListener(this);
        botonModificarTarifaServicio = new JButton("Modificar tarifa se servicio");
        botonModificarTarifaServicio.addActionListener(this);
        botonCrearProductoRestaurante = new JButton("Crear producto de restaurante");
        botonCrearProductoRestaurante.addActionListener(this);
        botonConsultarTarifas = new JButton("Consultar tarifas sin establecer para el proximo año");
        botonConsultarTarifas.addActionListener(this);
        botonVerDiagramaOcupacion = new JButton("Ver diagrama de ocupacion");
        botonVerDiagramaOcupacion.addActionListener(this);
        cambiarUsuario = new JButton("Cambiar de usuario");
        cambiarUsuario.addActionListener(this);

        
        panel.add(botonCrearHabitacion);
        panel.add(botonCargarTarifaHabitacion);
        panel.add(botonCrearServicio);
        panel.add(botonModificarTarifaServicio);
        panel.add(botonCrearProductoRestaurante);
        panel.add(botonConsultarTarifas);
        panel.add(botonVerDiagramaOcupacion);
        panel.add(cambiarUsuario);
        

        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setHorizontalAlignment(SwingConstants.CENTER);
                ((JButton) component).setVerticalAlignment(SwingConstants.CENTER);
                ((JButton) component).setPreferredSize(new Dimension(250, 100));
            }
        }
        
        add(panel);
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCrearHabitacion) {
            CrearHabitacion crearHabitacion = new CrearHabitacion(hotel.getControladorHabitaciones());
            crearHabitacion.setVisible(true);
        } else if (e.getSource() == botonCargarTarifaHabitacion) {
            CrearTarifa crearTarifa = new CrearTarifa(hotel.getControladorHabitaciones());
            crearTarifa.setVisible(true);
        } else if (e.getSource() == botonModificarTarifaServicio) {
            ModificarTarifaServicios modificartarifa = new ModificarTarifaServicios(hotel.getControladorServicios());
            modificartarifa.setVisible(true);
        } else if (e.getSource() == botonCrearServicio){
            CrearServicio crearServicio = new CrearServicio(hotel.getControladorServicios());
            crearServicio.setVisible(true);
        } else if (e.getSource() == botonCrearProductoRestaurante) {
           CrearProductoRestaurante crearProductoRestaurante = new CrearProductoRestaurante(hotel.getControladorServicios());
           crearProductoRestaurante.setVisible(true);
        } else if (e.getSource() == botonConsultarTarifas) {
            ConsultarTarifas consultarTarifas =  new ConsultarTarifas(hotel.getControladorHabitaciones());
            consultarTarifas.setVisible(true);
        } else if (e.getSource() == botonVerDiagramaOcupacion) {
            ConsultarOcupacion consultarOcupacion = new ConsultarOcupacion(hotel.getControladorHabitaciones());
            consultarOcupacion.setVisible(true);
        }   else if(e.getSource() == cambiarUsuario){
            setVisible(false);
            new IniciarInterfaz();
        }
    }
}