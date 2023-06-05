package InterfazDeHuesped;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Modelo.Hotel;

import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class CrearUsuario extends JDialog implements ActionListener{

    private JButton botonCerrar;
    private JButton botonCrear;
    private JTextField textFieldUser;
    private JTextField textFieldPassword;
    private JTextField textFieldNombre;
    private Hotel Hotel;


    public CrearUsuario(Hotel Hotel){

        this.Hotel = Hotel;

        setLayout(new GridLayout(4,2));
        setSize(400, 600);
        
        botonCerrar = new JButton("Cerrar", null);
        botonCerrar.addActionListener(this);

        botonCrear = new JButton("Crear");
        botonCrear.addActionListener(this);

        textFieldUser = new JTextField();
        textFieldPassword = new JTextField();
        textFieldNombre = new JTextField();

        JLabel textArea1 = new JLabel("Usuario: "); 
        JLabel textArea2 = new JLabel("Contraseña: "); 
        JLabel textArea3 = new JLabel("Nombre: "); 

        add(textArea1); add(textFieldUser);
        add(textArea2); add(textFieldPassword);
        add(textArea3); add(textFieldNombre);
        add(botonCrear); add(botonCerrar);

        setVisible(true);
}

public void actionPerformed(ActionEvent e) {
    if(e.getSource() == botonCerrar){
        setVisible(false);
    }

    else if(e.getSource() == botonCrear){
       String usuario =  textFieldUser.getText();
       String nombre = textFieldNombre.getText();
       String password = textFieldPassword.getText();
       crearUsuario(nombre, usuario, password);
       setVisible(false);
       JOptionPane.showMessageDialog(null, "Usuario creado exitósamente");
    }
}

public void crearUsuario(String nombre, String usuario, String contraseña){
    Hotel.addUsuario(nombre, usuario, contraseña, "Huesped");
    try {
        Files.write(Paths.get("./Entrega2Proyecto2/Datos/Usuarios.txt"),("\n"+nombre+";"+usuario+";"+contraseña+";Huesped").getBytes(), StandardOpenOption.APPEND ) ;
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
}}