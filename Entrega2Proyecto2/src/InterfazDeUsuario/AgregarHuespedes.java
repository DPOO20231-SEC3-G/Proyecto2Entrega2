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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AgregarHuespedes extends JDialog implements ActionListener {

    private CrearReserva crearReserva;

    private GenerarLog generarLog;

    private int value;

    private JList<String> lista;

    private JScrollPane scroll;
    
    private DefaultListModel<String> model;

    private ArrayList<ArrayList<String>> listaHuespedes;

    private JLabel lNombre;

    private JTextArea tNombre;

    private  JLabel lDocumento;

    private JTextArea tDocumento;

    private JLabel lCorreo;

    private JTextArea tCorreo;

    private JLabel lCelular;

    private JTextArea tCelular;

    private JCheckBox cBNecesitaCama;

    private JButton bGuardar;


    public AgregarHuespedes(CrearReserva nCrearReserva){

        crearReserva = nCrearReserva;

        setLayout(new BorderLayout());
        setSize(500,300);
        setResizable(false);
        model = new DefaultListModel<String>();
        value = 1;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
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

        lista = new JList<String>(model);
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

        panelDerecho.setSize(250, 300);

        add(panelDerecho, BorderLayout.EAST);

        setVisible(true);

    }

    public AgregarHuespedes(GenerarLog nGenerarLog){
        generarLog = nGenerarLog;
        value = 0;

        setLayout(new BorderLayout());
        setSize(500,300);
        setResizable(false);
        model = new DefaultListModel<String>();
        
        listaHuespedes = generarLog.getHuespedes();

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

        panelDerecho.setSize(250, 300);

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
        
        if(continuo && value == 1){
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
            crearReserva.setnHuespedes(crearReserva.getnHuespedes()+1);
            crearReserva.actualizarNHuespedes();
            AgregarHuespedes.this.dispose();
        }
        else if(continuo && value == 0){
            ArrayList<String> huesped = new ArrayList<String> ();
            huesped.add(tNombre.getText());
            huesped.add(tDocumento.getText());
            huesped.add(tCorreo.getText());
            huesped.add(tCelular.getText());
            if (cBNecesitaCama.isSelected()){
                huesped.add("true");
            } else {huesped.add("false");}
            listaHuespedes.add(huesped);
            generarLog.setHuespedes(listaHuespedes);
            JOptionPane.showMessageDialog(null,"Se guardo la informacion del huesped exitosamente", "Alerta", JOptionPane.INFORMATION_MESSAGE );
            AgregarHuespedes.this.dispose();
        }
    }
    
}
