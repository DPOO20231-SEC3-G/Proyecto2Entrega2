package InterfazDeHuesped;

import javax.swing.JButton;
import javax.swing.JFrame;

import InterfazDeUsuario.ConsultarOcupacion;
import InterfazDeUsuario.CrearReserva;
import Modelo.Hotel;

import java.awt.GridLayout;
import java.awt.event.*;

public class InterfazHuesped extends JFrame implements ActionListener {
    private JButton crearReserva;
    private JButton verOcupacion;
    private JButton pagarReserva;
    private Hotel Hotel;
    private JButton cambiarUsuario;
    private JButton verDispHabitacion;

    public InterfazHuesped(Hotel Hotel){
        
        this.Hotel = Hotel;
        
        setLayout(new GridLayout(1,5));
        setSize(800, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        verOcupacion = new JButton("Ver diagrama de \n ocupacion");
        verOcupacion.addActionListener(this);
        crearReserva = new JButton("Reservar Habitación");
        crearReserva.addActionListener(this);
        pagarReserva = new JButton("Pagar Reserva");
        pagarReserva.addActionListener(this);
        cambiarUsuario = new JButton("Cambiar Usuario");
        cambiarUsuario.addActionListener(this);
        verDispHabitacion = new JButton("Ver disponibilidad \n habitacion");
        verDispHabitacion.addActionListener(this);

        add(verOcupacion);
        add(crearReserva);
        add(pagarReserva);
        add(verDispHabitacion);
        add(cambiarUsuario);

        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==verOcupacion){
            ConsultarOcupacion consultarOcupacion = new ConsultarOcupacion(Hotel.getControladorHabitaciones());
            consultarOcupacion.setVisible(true);
        }
        else if(e.getSource()==crearReserva){
            CrearReserva crearReserva = new CrearReserva(Hotel);
            crearReserva.setVisible(true);
        }
        else if (e.getSource()==pagarReserva){
        	RealizarPago realizarPago = new RealizarPago(Hotel);
        	
        }
        else if(e.getSource()==cambiarUsuario){
            setVisible(false);
            IniciarInterfazH IniciarInterfazH = new IniciarInterfazH(true,null);
        }
        else if(e.getSource() == verDispHabitacion){
            DispHabitacion dispHabitacion = new DispHabitacion(Hotel);
            dispHabitacion.setVisible(true);
        }
    }
    
}
