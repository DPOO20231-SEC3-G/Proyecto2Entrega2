package InterfazDeUsuario;

import javax.swing.*;

import Modelo.ControladorServicios;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

public class CrearServicio extends JDialog implements ActionListener {

    private JButton botonCerrar;
    private JButton botonCrear;
    private JTextField textFieldCobro;
    private JTextField textFieldPrecios;
    private JTextField textFieldNombre;
    private JTextField textFieldLugar;

    private ControladorServicios controladorServicios;
    
    public CrearServicio(ControladorServicios controladorServicios){
        this.controladorServicios = controladorServicios;

        setLayout(new GridLayout(5,2));
        setTitle("Crear Servicio");
        setSize(300, 300);

        botonCerrar = new JButton("Cerrar");
        botonCrear = new JButton("Crear Servicio");
        botonCerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botonCerrar.addActionListener(this);
        botonCrear.addActionListener(this);

        JTextArea textArea1 = new JTextArea("Tipo Cobro: "); textArea1.setFont(new Font("Arial", Font.BOLD,12)); textArea1.setEditable(false);
        JTextArea textArea2 = new JTextArea("Precio: "); textArea2.setFont(new Font("Arial", Font.BOLD,12)); textArea2.setEditable(false);
        JTextArea textArea3 = new JTextArea("Nombre: "); textArea3.setFont(new Font("Arial", Font.BOLD,12)); textArea3.setEditable(false);        
        JTextArea textArea4 = new JTextArea("Lugar: "); textArea4.setFont(new Font("Arial", Font.BOLD,12)); textArea4.setEditable(false);   
        JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

        textFieldCobro = new JTextField();
        textFieldPrecios= new JTextField();
        textFieldNombre = new JTextField();
        textFieldLugar = new JTextField();
        

        add(textArea3);add(textFieldNombre);
        add(textArea4);add(textFieldLugar);
        add(textArea1);add(textFieldCobro);
        add(textArea2);add(textFieldPrecios);
        add(botonCrear);add(botonCerrar);

        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonCerrar){
            setVisible(false);
        }

        else if(e.getSource() == botonCrear){
            String Nombre = textFieldNombre.getText();
            double Precio = Double.parseDouble(textFieldPrecios.getText());
            String Lugar = textFieldLugar.getText();
            String Cobro = textFieldCobro.getText();
            controladorServicios.crearServicio(Nombre, Cobro, Lugar, Precio);
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Servicio creado exitosamente");

        }
    }
}
