package InterfazDeUsuario;

import javax.swing.*;

import Modelo.ControladorServicios;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

public class CrearProductoRestaurante extends JDialog implements ActionListener {

    private JButton botonCerrar;
    private JButton botonCrear;
    private JComboBox<String> TiposBox;
    private JTextField textFieldHoras;
    private JTextField textFieldPrecios;
    private JTextField textFieldNombre;

    private ControladorServicios controladorServicios;
    
    public CrearProductoRestaurante(ControladorServicios controladorServicios){
        this.controladorServicios = controladorServicios;

        setLayout(new GridLayout(5,2));
        setTitle("Crear producto restaurante");
        setSize(300, 300);

        botonCerrar = new JButton("Cerrar");
        botonCrear = new JButton("Crear Producto");
        botonCerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botonCerrar.addActionListener(this);
        botonCrear.addActionListener(this);

        JTextArea textArea1 = new JTextArea("Rango de horas: (##:## - ##:##)"); textArea1.setFont(new Font("Arial", Font.BOLD,12)); textArea1.setEditable(false);
        JTextArea textArea2 = new JTextArea("Precio: "); textArea2.setFont(new Font("Arial", Font.BOLD,12)); textArea2.setEditable(false);
        JTextArea textArea3 = new JTextArea("Nombre: "); textArea3.setFont(new Font("Arial", Font.BOLD,12)); textArea3.setEditable(false);        
        JTextArea textArea4 = new JTextArea("Tipo de producto: "); textArea4.setFont(new Font("Arial", Font.BOLD,12)); textArea4.setEditable(false);   
        JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

        textFieldHoras = new JTextField();
        textFieldPrecios= new JTextField();
        textFieldNombre = new JTextField();
        String[] Tipos = {"Bebida","Comida"};
        TiposBox = new JComboBox<>(Tipos);

        add(textArea3);add(textFieldNombre);
        add(textArea4);add(TiposBox);
        add(textArea1);add(textFieldHoras);
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
            String Horas = textFieldHoras.getText();
            String Tipo = TiposBox.getItemAt(TiposBox.getSelectedIndex());

            controladorServicios.crearProductoRestaurante(Nombre, Tipo, Horas, Precio);
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Producto creado exitosamente");

        }
    }
}
