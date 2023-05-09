package InterfazDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Modelo.Hotel;
import Modelo.Huesped;

public class GenerarLog extends JDialog implements ActionListener, KeyListener {

    Hotel hotel;

    JLabel lNHuespedes;

    JTextArea taNHuespedes;

    JButton bConfirmarHuespedes;

    JButton bAgregarHuesped;

    JButton bEliminarHuesped;

    JPanel panelSuperior;

    ArrayList<ArrayList<String>> huespedes = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> getHuespedes() {
        return this.huespedes;
    }

    public void setHuespedes(ArrayList<ArrayList<String>> huespedes) {
        this.huespedes = huespedes;
    }

    public GenerarLog(Hotel nHotel){

        hotel = nHotel;

        setLayout(new BorderLayout());

        setSize(500,200);

        setResizable(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panelSuperior = new JPanel();

        panelSuperior.setLayout(new GridLayout(2,2));

        lNHuespedes = new JLabel("Ingrese el numero de huespedes: ");
        panelSuperior.add(lNHuespedes);

        taNHuespedes = new JTextArea();
        taNHuespedes.addKeyListener(this);
        panelSuperior.add(taNHuespedes);

        bAgregarHuesped = new JButton("Agregar Huesped");
        bAgregarHuesped.addActionListener(this);
        panelSuperior.add(bAgregarHuesped);

        bEliminarHuesped = new JButton("Eliminar Huesped");
        bEliminarHuesped.addActionListener(this);
        panelSuperior.add(bEliminarHuesped);

        add(panelSuperior, BorderLayout.CENTER);

        bConfirmarHuespedes = new JButton("Confirmar");
        bConfirmarHuespedes.addActionListener(this);
        add(bConfirmarHuespedes, BorderLayout.SOUTH);

        setVisible(true);


    }

    public Hotel getHotel(){
        return this.hotel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == bConfirmarHuespedes){
            Integer tamano = Integer.parseInt(taNHuespedes.getText());
            if(!tamano.equals(huespedes.size())){
                JOptionPane.showMessageDialog(null,"No concuerda el numero de huespedes ingresado con la informacion de huespedes ingresado", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            }
            else{
                hotel.archivoLog(huespedes);
                JOptionPane.showMessageDialog(null,"Se ha generado el archivo log exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE );
                GenerarLog.this.dispose();
            }
        }else if(e.getSource() == bAgregarHuesped){
            new AgregarHuespedes(this);
        }else if(e.getSource() == bEliminarHuesped){
            new EliminarHuespedes(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    
}
