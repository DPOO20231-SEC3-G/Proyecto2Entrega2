package InterfazDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Modelo.ControladorHabitaciones;
import Modelo.Habitacion;
import Modelo.Reserva;

public class VerInventario extends JDialog{


    private JScrollPane panelDesplazamiento;
    
    public VerInventario(ControladorHabitaciones controladorHabitaciones){

        setTitle("Inventario");
        setSize(400, 500);

        JPanel panelEtiqueta = new JPanel();
        JLabel etiqueta = new JLabel("Lista de Habitaciones:");
        panelEtiqueta.add(etiqueta);

        JPanel panel =  agregar_inventario(controladorHabitaciones);

        
        panelDesplazamiento = new JScrollPane(panel);
        panelDesplazamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        getContentPane().add(panelEtiqueta, BorderLayout.NORTH);
        getContentPane().add(panelDesplazamiento, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);

    }

    public JPanel agregar_inventario(ControladorHabitaciones controladorHabitaciones){

        Border borde = BorderFactory.createEmptyBorder(5,5,5,5); 

        ArrayList<Habitacion> habitaciones = controladorHabitaciones.getHabitaciones();

        JPanel panelRetorno = new JPanel(); panelRetorno.setLayout(new BoxLayout(panelRetorno, BoxLayout.Y_AXIS));
        
        int n = 1;
        for(Habitacion habitacion : habitaciones){
            JPanel panel = new JPanel(new GridLayout(5,2));
            JTextArea textAreaHabitacion = new JTextArea("Habitacion " + n + "."); textAreaHabitacion.setEditable(false);
            JTextArea textAreaId = new JTextArea("Ubicacion: " + habitacion.getUbicacion()); textAreaId.setEditable(false);
            JTextArea tipoHabitacion = new JTextArea("Tipo de habitacion: " + habitacion.getTipoHabitacion()); tipoHabitacion.setEditable(false);
            JTextArea capacidad = new JTextArea("Capacidad: " + habitacion.getEspacio() + " personas."); capacidad.setEditable(false);
            JTextArea balcon = new JTextArea("Balcon: " + habitacion.isBalcon()); balcon.setEditable(false);
            JTextArea  vista = new JTextArea("Vista: " + habitacion.isVista()); vista.setEditable(false);
            JTextArea cocina = new JTextArea("Cocina integrada: " + habitacion.isCocinaIntegrada()); cocina.setEditable(false);
            JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

            JTextArea reservasText = new JTextArea("Reservas: "); reservasText.setEditable(false);

            JPanel panelReserva = null;
            for(Reserva reserva : habitacion.getReservas()){
                panelReserva = new JPanel(); panelReserva.setLayout(new BoxLayout(panelReserva, BoxLayout.Y_AXIS));
                JTextArea reservaTextArea = new JTextArea(reserva.stringInventario()); reservaTextArea.setEditable(false);
                panelReserva.setBorder(borde); 
                panelReserva.add(reservaTextArea);
            }
            JScrollPane scrollPaneReservas = new JScrollPane(panelReserva);
            scrollPaneReservas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            panel.add(textAreaHabitacion); panel.add(textAreaId);
            panel.add(tipoHabitacion); panel.add(capacidad);
            panel.add(cocina); panel.add(vista);
            panel.add(balcon); panel.add(hueco);
            panel.add(reservasText); panel.add(scrollPaneReservas);
            panel.setBorder(borde); 
            panelRetorno.add(panel);

            n ++;
        }

        return panelRetorno;

    }
    
}
