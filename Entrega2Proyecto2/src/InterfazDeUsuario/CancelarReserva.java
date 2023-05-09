package InterfazDeUsuario;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Modelo.ControladorReservas;
import Modelo.Hotel;
import Modelo.Huesped;
import Modelo.Reserva;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CancelarReserva extends JDialog implements ActionListener{
    Hotel hotel;

    ControladorReservas controladorReservas;

    JPanel panel;

    JPanel panelInferior;

    JScrollPane scroll;

    ArrayList<Reserva> reservas;

    JLabel lReserva;

    JRadioButton bReserva;

    JButton bCancelarReserva;

    JButton bSeleccionado;

    JLabel lSeleccionado;

    ArrayList<Huesped> huespedes;

    public CancelarReserva(ControladorReservas nControladorReservas){

        controladorReservas = nControladorReservas;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        reservas = controladorReservas.getReservas();

        panel = new JPanel();

        setLayout(new BorderLayout());

        setSize(500,500);
        
        setResizable(false);

        panel.setLayout(new GridLayout(reservas.size(),1));

        ButtonGroup group = new ButtonGroup();

        for (Reserva reserva: reservas){
            String cadena = "";
            cadena+= "Id: "+ String.valueOf(reserva.getIdReserva());
            cadena+= ", Habitacion: "+reserva.getHabitacion().getId();
            cadena+= ", Fechas: "+ reserva.getRangoFecha();
            cadena+= ", Huespedes: ";
            huespedes = reserva.getHuespedes();
            for(Huesped huesped: huespedes){
                cadena += "["+huesped.getDocumento()+"-"+huesped.getNombre()+"]";
            }
            bReserva = new  JRadioButton(cadena);
            bReserva.addActionListener(this);
            group.add(bReserva);
            panel.add(bReserva);
        }
        scroll = new JScrollPane(panel);
        add(scroll, BorderLayout.CENTER);

        panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(3,1));

        lReserva = new JLabel("Seleccionado:");
        panelInferior.add(lReserva);

        lSeleccionado = new JLabel("Sin seleccionar");
        panelInferior.add(lSeleccionado);

        bCancelarReserva = new JButton("Cancelar reserva");
        bCancelarReserva.addActionListener(this);
        panelInferior.add(bCancelarReserva);

        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bCancelarReserva){
            if (lSeleccionado.getText().equals("Sin seleccionar")){
                JOptionPane.showMessageDialog(null,"Seleccione una reserva para cancelar", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            }else{
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la reserva '"+lSeleccionado.getText()+"'' ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                    // El usuario eligió "Sí"
                        String seleccionado = lSeleccionado.getText();
                        int fin = seleccionado.indexOf(", Habitacion");
                        String id = seleccionado.substring(4, fin).strip();
                        if(controladorReservas.cancelarReserva(Integer.parseInt(id))){
                            JOptionPane.showMessageDialog(null,"Se cancelo exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE );
                            CancelarReserva.this.dispose();
                        }else{JOptionPane.showMessageDialog(null,"Se debe cancelar con almenos 48 horas de anticipacion", "Alerta", JOptionPane.INFORMATION_MESSAGE );}
                        
                }}
        }else{
        lSeleccionado.setText(((AbstractButton) e.getSource()).getText());
        }
    }



}
    
