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
    private JButton botonCargarTarifaServicio;
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
        

        botonCrearHabitacion = new JButton("Crear Habitacion para inventario"); botonCrearHabitacion.addActionListener(this);
        botonCargarTarifaHabitacion = new JButton("Cargar tarifa de habitación"); botonCargarTarifaHabitacion.addActionListener(this);
        botonCargarTarifaServicio = new JButton("Cargar tarifa de servicio"); botonCargarTarifaServicio.addActionListener(this);
        botonCrearProductoRestaurante = new JButton("Crear producto de restaurante"); botonCrearProductoRestaurante.addActionListener(this);
        botonConsultarTarifas = new JButton("Consultar tarifas sin establecer para el proximo año"); botonConsultarTarifas.addActionListener(this);
        botonVerDiagramaOcupacion = new JButton("Ver diagrama de ocupacion"); botonConsultarTarifas.addActionListener(this);
        cambiarUsuario = new JButton("Cambiar de usuario"); cambiarUsuario.addActionListener(this);
        
        panel.add(botonCrearHabitacion);
        panel.add(botonCargarTarifaHabitacion);
        panel.add(botonCargarTarifaServicio);
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
            // Acción cuando se presiona el botón "Cargar tarifa de habitación"
        } else if (e.getSource() == botonCargarTarifaServicio) {
            // Acción cuando se presiona el botón "Cargar tarifa de servicio"
        } else if (e.getSource() == botonCrearProductoRestaurante) {
            // Acción cuando se presiona el botón "Crear producto de restaurante"
        } else if (e.getSource() == botonConsultarTarifas) {
            // Acción cuando se presiona el botón "Consultar tarifas sin establecer para el próximo año"
        } else if (e.getSource() == botonVerDiagramaOcupacion) {
            // Acción cuando se presiona el botón "Ver diagrama de ocupación"
        }   else if(e.getSource() == cambiarUsuario){
            setVisible(false);
            new IniciarInterfaz();
        }
    }
}