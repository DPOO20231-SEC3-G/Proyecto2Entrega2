package InterfazDeUsuario;

import javax.swing.*;

import Modelo.ControladorHabitaciones;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;



public class CrearHabitacion extends JDialog implements ActionListener{
    private JButton botonCerrar;
    private JButton botonCrear;
    private JCheckBox checkBoxVista;
    private JCheckBox checkBoxBalcon;
    private JCheckBox checkBoxCocina;
    private JTextField textField;
    private JTextField textFieldCamas;
    private JComboBox<String> comboBox;

    private ControladorHabitaciones controladorHabitaciones;

    private JCheckBox aire;
    private JCheckBox calefaccion;
    private JCheckBox tv;
    private JCheckBox cafetera;
    private JCheckBox ropaCama;
    private JCheckBox tapetes;
    private JCheckBox plancha;
    private JCheckBox secador;
    private JCheckBox usabA;
    private JCheckBox usbC;
    private JCheckBox desayuno;

    private JTextField tamaño;
    private JTextField voltaje;

    public CrearHabitacion(ControladorHabitaciones controladorHabitaciones) {
        this.controladorHabitaciones = controladorHabitaciones;

        setLayout(new GridLayout(13,2));
        setSize(400, 600);
    
        botonCerrar = new JButton("Cerrar");
        botonCrear = new JButton("Crear Habitacion");

        botonCerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botonCerrar.addActionListener(this);
        botonCrear.addActionListener(this);
    
        JTextArea textArea = new JTextArea("Ubicación: "); textArea.setFont(new Font("Arial", Font.BOLD,12)); textArea.setEditable(false);
        aire = new JCheckBox("Aire acondicionado");
        textField = new JTextField();
        textFieldCamas = new JTextField();
        checkBoxVista = new JCheckBox("Vista"); 
        checkBoxBalcon = new JCheckBox("Balcon");
        checkBoxCocina = new JCheckBox("Cocina Integrada");
        String[] opciones = {"estandar","suite","suite doble"};
        comboBox = new JComboBox<>(opciones);
        JTextArea textArea2 = new JTextArea("Tipo de habitacion: ");textArea2.setEditable(false);
        JTextArea camas = new JTextArea("Numero de camas: "); camas.setEditable(false);

        calefaccion = new JCheckBox("Calefaccion");
        tv = new JCheckBox("Tiene TV");
        cafetera = new JCheckBox("Tiene cafetera");
        ropaCama = new JCheckBox("Tiene ropa de cama");
        tapetes = new JCheckBox("Tapetes hipoalergenicos");
        plancha = new JCheckBox("Tiene plancha");
        secador = new JCheckBox("Tiene secador");
        usabA = new JCheckBox("Cargador USB-A");
        usbC = new JCheckBox("Cargador USB-C");
        desayuno = new JCheckBox("Incluye desayuno");

        JTextArea tamanioJTextArea = new JTextArea("Tamanio en metros cuadrados: "); tamanioJTextArea.setEditable(false);
        JTextArea volt = new JTextArea("Voltaje AC: "); volt.setEditable(false);

        tamaño = new JTextField();
        voltaje = new JTextField();

        add(textArea); add(textField);
        add(checkBoxVista); add(checkBoxBalcon);
        add(checkBoxCocina); add(aire);
        add(textArea2); add(comboBox);
        add(camas); add(textFieldCamas);
        add(calefaccion); add(tv);
        add(cafetera); add(ropaCama);
        add(tapetes); add(plancha);
        add(secador); add(usabA);
        add(usbC); add(desayuno);
        add(volt); add(voltaje);
        add(tamanioJTextArea); add(tamaño);

        add(botonCrear); add(botonCerrar);
        


        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonCerrar){
            setVisible(false);
        }

        else if(e.getSource() == botonCrear){

            String ubicacion = textField.getText();
            Boolean balcon = checkBoxBalcon.isSelected();
            Boolean vista = checkBoxVista.isSelected();
            Boolean cocinaIntegrada =  checkBoxCocina.isSelected();
            String tipoHabitacion = comboBox.getItemAt(comboBox.getSelectedIndex());
            int numCamas = Integer.parseInt(textFieldCamas.getText());

            ArrayList<ArrayList<String>> infoCamas = new ArrayList<ArrayList<String>>();

            
            for(int i=0;i < numCamas;i++){
                ArrayList<String> info = new ArrayList<String>();
                LabelCamas labelCamas = new LabelCamas(botonCerrar, i);

                String tamaño = labelCamas.getComboBox1().getItemAt(comboBox.getSelectedIndex());
                String cantidadPersonas = labelCamas.getComboBox2().getItemAt(comboBox.getSelectedIndex());
                String soloNiños = "";
                if(labelCamas.getCheckBoxNiños().isSelected()){soloNiños = "true";}
                else{soloNiños = "false";}

                info.add(tamaño); info.add(cantidadPersonas); info.add(soloNiños);
                infoCamas.add(info);

            }

            controladorHabitaciones.crearHabitacion(ubicacion, balcon, vista, cocinaIntegrada, tipoHabitacion, infoCamas,
            Float.parseFloat(tamaño.getText()),aire.isSelected(),calefaccion.isSelected(),tv.isSelected(),cafetera.isSelected(),
            ropaCama.isSelected(),tapetes.isSelected(),plancha.isSelected(),secador.isSelected(), Integer.parseInt(voltaje.getText()),usabA.isSelected(),
            usbC.isSelected(),desayuno.isSelected());
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Habitacion creada exitosamente");

        }
    }
    private class LabelCamas extends JPanel{

        private JComboBox<String> comboBox1;
        private JComboBox<String> comboBox2;
        private JCheckBox checkBoxNiños;

        private LabelCamas(JButton cerrar, int n ){
        setLayout(new GridLayout(3,2));

        JTextArea tamanio = new JTextArea("Tamanio cama "+(n+1) + ": "); tamanio.setEditable(false);
        JTextArea cantidad = new JTextArea("Capacidad cama "+(n+1) + ": "); cantidad.setEditable(false);
        JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

        String[] opciones1 = {"individual","doble","queen","king"};
        String[] opciones2 = {"1","2","3"};
        checkBoxNiños = new JCheckBox("Solo para niños");

        comboBox1 = new JComboBox<>(opciones1);
        comboBox2 = new JComboBox<>(opciones2);

        add(tamanio); add(comboBox1); 
        add(cantidad); add(comboBox2); 
        add(checkBoxNiños); add(hueco);

        JOptionPane.showMessageDialog(null,this);
    
        }

        public JComboBox<String> getComboBox1() {
            return comboBox1;
        }

        public JComboBox<String> getComboBox2() {
            return comboBox2;
        }

        public JCheckBox getCheckBoxNiños() {
            return checkBoxNiños;
        }
        
    }
    
}
