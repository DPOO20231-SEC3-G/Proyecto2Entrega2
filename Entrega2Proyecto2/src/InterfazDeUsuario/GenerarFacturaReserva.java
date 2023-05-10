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

public class GenerarFacturaReserva  extends JDialog implements ActionListener {

    private Hotel hotel;

    private ControladorReservas controladorReservas;

    private JPanel panel;

    private JPanel panelInferior;

    private JScrollPane scroll;

    private ArrayList<Reserva> reservas;

    private JLabel lReserva;

    private JRadioButton bReserva;

    private JButton bGenerarFactura;

    private JButton bSeleccionado;

    private JLabel lSeleccionado;

    private ArrayList<Huesped> huespedes;

    public GenerarFacturaReserva(Hotel nHotel){

        hotel = nHotel;
        controladorReservas = hotel.getControladorReservas();
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

        bGenerarFactura = new JButton("Generar Factura");
        bGenerarFactura.addActionListener(this);
        panelInferior.add(bGenerarFactura);

        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bGenerarFactura){
            if (lSeleccionado.getText().equals("Sin seleccionar")){
                JOptionPane.showMessageDialog(null,"Seleccione una reserva antes generar la factura", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            }else{
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea generar factura de la reserva '"+lSeleccionado.getText()+"'' ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                    // El usuario eligió "Sí"
                        String seleccionado = lSeleccionado.getText();
                        int fin = seleccionado.indexOf(", Habitacion");
                        String id = seleccionado.substring(4, fin).strip();
                        Reserva reserva = controladorReservas.getReservaId(Integer.parseInt(id)+1);
                        hotel.GenerarFacturaReserva(reserva);
                        GenerarFacturaReserva.this.dispose();
                }}
        }else{
        lSeleccionado.setText(((AbstractButton) e.getSource()).getText());
        }
    }



}