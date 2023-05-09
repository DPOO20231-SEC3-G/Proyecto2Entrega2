package InterfazDeUsuario;

import javax.security.auth.x500.X500Principal;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

import Modelo.Hotel;


public class CrearReserva extends JDialog implements ActionListener{

    private Hotel hotel;

    private JButton bAgregarHuesped;
    private JButton bEliminarHuesped;
    private JButton bCrearReserva;
    private JButton bVerificarDisponibilidad;
    private JLabel lNumeroHuespedes;
    private JLabel lNumeroDeHuespedes;
    private JLabel lFechaInicio;
    private JLabel lFechaFinal;
    private JLabel lIdHabitacion;
    private JLabel lDisponibilidad;
    private JTextArea tFechaInicio;
    private JTextArea tFechaFinal;
    private JTextArea tIdHabitacion;
    
    private String fechaInicial;
    private String fechaFinal;
    private String idHabitacionText;
    private int nHuespedes = 0;


    public int getnHuespedes() {
        return nHuespedes;
    }

    public void setnHuespedes(int nHuespedes) {
        this.nHuespedes = nHuespedes;
    }

    private ArrayList<ArrayList<String>> huespedes = new ArrayList<ArrayList<String>>() ;

    public ArrayList<ArrayList<String>> getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(ArrayList<ArrayList<String>> huespedes) {
        this.huespedes = huespedes;
    }

    public void actualizarNHuespedes(){
        lNumeroDeHuespedes.setText(String.valueOf(nHuespedes));
    }
    

    public CrearReserva(Hotel nhotel){

        hotel = nhotel;

        setLayout(new GridLayout(7,2));
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        bAgregarHuesped= new JButton("Agregar huesped");
        bAgregarHuesped.addActionListener(this);
        bEliminarHuesped= new JButton("Eliminar Huesped");
        bEliminarHuesped.addActionListener(this);
        bCrearReserva= new JButton("Crear Reserva");
        bCrearReserva.addActionListener(this);
        bVerificarDisponibilidad= new JButton("Verificar Disponibilidad");
        bVerificarDisponibilidad.addActionListener(this);
        lNumeroHuespedes = new JLabel("Número de huespedes: ");
        lNumeroDeHuespedes = new JLabel("0");
        lFechaInicio = new JLabel("Fecha Inicio: ");
        lFechaFinal = new JLabel("Fecha Final: ");
        lIdHabitacion = new JLabel("Id habitación: ");
        lDisponibilidad = new JLabel("No Disponible");
        lDisponibilidad.setForeground(Color.RED);
        tFechaInicio = new JTextArea();
        tFechaFinal = new JTextArea();
        tIdHabitacion = new JTextArea();
        

        add(lNumeroHuespedes);add(lNumeroDeHuespedes);
        add(bAgregarHuesped);add(bEliminarHuesped);
        add(lFechaInicio);add(tFechaInicio);
        add(lFechaFinal);add(tFechaFinal);
        add(lIdHabitacion);add(tIdHabitacion);
        add(bVerificarDisponibilidad);add(lDisponibilidad);
        add(bCrearReserva);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == bCrearReserva){
            idHabitacionText = tIdHabitacion.getText();
            fechaInicial = tFechaInicio.getText();
            fechaFinal = tFechaFinal.getText();

        if (hotel.confirmarDisponibilidad(fechaInicial, fechaFinal, idHabitacionText)){
            if(huespedes.size()>0){
                try {
                    hotel.crearReserva(huespedes, fechaInicial, fechaFinal, idHabitacionText);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Se creo la reserva exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Añada huespedes a la reserva", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Verifique los datos ingresados", "Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        }
        else if(e.getSource() == bAgregarHuesped){
            AgregarHuespedes agregarHuespedes = new AgregarHuespedes(this);
        }
        else if(e.getSource() == bEliminarHuesped){
            EliminarHuespedes eliminarHuespedes = new EliminarHuespedes(this);
        }
        else if(e.getSource() == bVerificarDisponibilidad){
        idHabitacionText = tIdHabitacion.getText();
        fechaInicial = tFechaInicio.getText();
        fechaFinal = tFechaFinal.getText();
        if (hotel.confirmarDisponibilidad(fechaInicial, fechaFinal, idHabitacionText)){
            lDisponibilidad.setText("Disponible");
            lDisponibilidad.setForeground(Color.GREEN);
        }else{
            lDisponibilidad.setText("No Disponible");
            lDisponibilidad.setForeground(Color.RED);
        }
        }
    }


    

    
    
}
