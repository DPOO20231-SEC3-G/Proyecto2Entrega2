package InterfazDeHuesped;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import InterfazDeUsuario.ConsultarOcupacion;
import InterfazDeUsuario.CrearReserva;
import Modelo.Hotel;

import java.awt.GridLayout;
import java.awt.event.*;

public class DispHabitacion extends JFrame implements ActionListener {

    private JButton botonBuscar;
    private JTextField idHabitacion;
    private JTextField fechaInicial;
    private JTextField fechaFinal;
    private Hotel Hotel;
    private JButton botonCerrar;

    public DispHabitacion(Hotel Hotel){
        
        this.Hotel = Hotel;

        setLayout(new GridLayout(4,2));
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botonBuscar = new JButton("Buscar", null);
        botonBuscar.addActionListener(this);
        idHabitacion = new JTextField();
        fechaFinal = new JTextField();
        fechaInicial = new JTextField();
        botonCerrar = new JButton("Cerrar");
        
        JLabel label1 = new JLabel("ID Habitacion");
        JLabel label2 = new JLabel("Fecha inicial");
        JLabel label3 = new JLabel("Fecha Final");

        add(label1);add(idHabitacion);
        add(label2);add(fechaInicial);
        add(label3);add(fechaFinal);
        add(botonBuscar);add(botonCerrar);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonCerrar){
            setVisible(false);
        }
    
        else if(e.getSource() == botonBuscar){
           String finicial =  fechaInicial.getText();
           String id = idHabitacion.getText();
           String ffinal = fechaFinal.getText();
           boolean disp = Hotel.confirmarDisponibilidad(finicial, ffinal, id);
           if(disp){
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Habitación disponible en las fechas indicadas");
           }
           else{
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Habitación NO disponible en las fechas indicadas");
           }
        }
    }

}
