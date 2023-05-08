package InterfazDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Modelo.Hotel;

public class AgregarHuespedes extends JDialog implements ActionListener {

    CrearReserva crearReserva;

    JList lista;

    JScrollPane scroll;
    
    DefaultListModel<String> model;

    ArrayList<ArrayList<String>> listaHuespedes;

    JLabel lNombre;

    JTextArea tNombre;

    JLabel lDocumento;

    JTextArea tDocumento;

    JLabel lCorreo;

    JTextArea tCorreo;

    JLabel lCelular;

    JTextArea tCelular;

    JCheckBox cBNecesitaCama;

    JButton bGuardar;


    public AgregarHuespedes(CrearReserva nCrearReserva){

        crearReserva = nCrearReserva;

        setLayout(new BorderLayout());
        setSize(500,500);
        model = new DefaultListModel<String>();
        
        listaHuespedes = crearReserva.getHuespedes();

        for (ArrayList<String> info : listaHuespedes) {
            String cadena= "";
            cadena += "Nombre: "+info.get(0);
            cadena += ", Documento: "+info.get(1);
            cadena += ", Email: "+info.get(2);
            cadena += ", Celular: "+info.get(3);
            cadena += ", Necesita Cama: "+info.get(4);
            model.addElement(cadena);
        }

        JList<String> lista = new JList<String>(model);
        scroll = new JScrollPane();

        scroll.setViewportView(lista);
        add(scroll, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(10,1));

        lNombre = new JLabel("Nombre:");
        panelDerecho.add(lNombre);

        tNombre = new JTextArea();
        panelDerecho.add(tNombre);

        lDocumento = new JLabel("Documento:");
        panelDerecho.add(lDocumento);

        tDocumento = new JTextArea();
        panelDerecho.add(tDocumento);

        lCorreo = new JLabel("Correo:");
        panelDerecho.add(lCorreo);
        
        tCorreo = new JTextArea();
        panelDerecho.add(tCorreo);

        lCelular = new JLabel("Celular:");
        panelDerecho.add(lCelular);

        tCelular = new JTextArea();
        panelDerecho.add(tCelular);

        cBNecesitaCama = new JCheckBox("Necesita cama: ");
        panelDerecho.add(cBNecesitaCama);

        bGuardar = new JButton("Guardar Huesped");
        bGuardar.addActionListener(this);
        panelDerecho.add(bGuardar);

        add(panelDerecho, BorderLayout.EAST);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean continuo = true;
        if(tNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No escribio nombre", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            continuo = false;
        }
        if(tDocumento.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No escribio documento", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            continuo = false;
        }
        if(tCorreo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No escribio correo", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            continuo = false;
        }
        if(tCelular.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"No escribio celular", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            continuo = false;
        }
        
        if(continuo){
            ArrayList<String> huesped = new ArrayList<String> ();
            huesped.add(tNombre.getText());
            huesped.add(tDocumento.getText());
            huesped.add(tCorreo.getText());
            huesped.add(tCelular.getText());
            if (cBNecesitaCama.isSelected()){
                huesped.add("true");
            } else {huesped.add("false");}
            listaHuespedes.add(huesped);
            crearReserva.setHuespedes(listaHuespedes);
            JOptionPane.showMessageDialog(null,"Se guardo la informacion del huesped exitosamente", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            AgregarHuespedes.this.dispose();
        }
    }
    
}
