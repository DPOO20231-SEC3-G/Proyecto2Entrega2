package InterfazDeUsuario;

import javax.swing.*;

import Modelo.Hotel;
import Modelo.Usuario;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class InterfazRecepcionista extends JFrame implements ActionListener {
    private JButton botonCrearReserva;
    private JButton botonGenerarFactura;
    private JButton botonConsultarInventario;
    private JButton botonCancelarReserva;
    private JButton botonGenerarArchivoLog;
    private JButton cambiarUsuario;

    private Hotel hotel;

    public InterfazRecepcionista(Hotel hotel, Usuario user) {

        this.hotel = hotel;

        setTitle("Bienvenid@: " +user.getRol() + "  "+ user.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        

        botonCrearReserva = new JButton("Crear reserva"); botonCrearReserva.addActionListener(this);
        botonGenerarFactura = new JButton("Generar Factura"); botonGenerarFactura.addActionListener(this);
        botonConsultarInventario = new JButton("Consultar inventario"); botonConsultarInventario.addActionListener(this);
        botonCancelarReserva = new JButton("Cancelar Reserva"); botonCancelarReserva.addActionListener(this);
        botonGenerarArchivoLog = new JButton("Generar Archivo Log"); botonGenerarArchivoLog.addActionListener(this);
        cambiarUsuario = new JButton("Cambiar de usuario"); cambiarUsuario.addActionListener(this);
        
        panel.add(botonCrearReserva);
        panel.add(botonGenerarFactura);
        panel.add(botonConsultarInventario);
        panel.add(botonCancelarReserva);
        panel.add(botonGenerarArchivoLog);
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
        if (e.getSource() == botonCrearReserva) {
            new CrearReserva(hotel);
        } else if (e.getSource() == botonGenerarFactura) {
            new GenerarFacturaReserva(hotel);
        } else if (e.getSource() == botonConsultarInventario) {
            new VerInventario(hotel.getControladorHabitaciones());
        } else if (e.getSource() == botonCancelarReserva) {
            new CancelarReserva(hotel.getControladorReservas());
        } else if (e.getSource() == botonGenerarArchivoLog) {
            new GenerarLog(hotel);
        } else if(e.getSource() == cambiarUsuario){
            setVisible(false);
            new IniciarInterfaz(true,null);
        }
        
    }

    public JButton getBotonCrearReserva() {
        return botonCrearReserva;
    }

    public JButton getBotonGenerarFactura() {
        return botonGenerarFactura;
    }

    public JButton getBotonConsultarInventario() {
        return botonConsultarInventario;
    }

    public JButton getBotonCancelarReserva() {
        return botonCancelarReserva;
    }

    public JButton getBotonGenerarArchivoLog() {
        return botonGenerarArchivoLog;
    }

    public Hotel getHotel() {
        return hotel;
    }
    
}
