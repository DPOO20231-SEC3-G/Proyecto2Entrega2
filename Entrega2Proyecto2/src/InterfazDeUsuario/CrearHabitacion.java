package InterfazDeUsuario;

import javax.swing.*;

import Modelo.ControladorHabitaciones;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;



public class CrearHabitacion extends JDialog implements ActionListener{
    private JButton botonCerrar;
    private JButton botonCrear;
    private LabelUbicacionCamas labelUbicacion;
    private JCheckBox checkBoxVista;
    private JCheckBox checkBoxBalcon;
    private JCheckBox checkBoxCocina;
    private JTextField textField;
    private JTextField textFieldCamas;
    private JComboBox<String> comboBox;

    private ControladorHabitaciones controladorHabitaciones;

    public CrearHabitacion(ControladorHabitaciones controladorHabitaciones) {
        this.controladorHabitaciones = controladorHabitaciones;

        BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);
        setSize(300, 300);
    
        botonCerrar = new JButton("Cerrar");
        botonCrear = new JButton("Crear Habitacion");

        botonCerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botonCerrar.addActionListener(this);
        botonCrear.addActionListener(this);
    
        JTextArea textArea = new JTextArea("Ubicación: "); textArea.setFont(new Font("Arial", Font.BOLD,12));
        textField = new JTextField();
        labelUbicacion = new LabelUbicacionCamas(textArea, textField);
        add(labelUbicacion);

        checkBoxVista = new JCheckBox("Vista"); 
        checkBoxBalcon = new JCheckBox("Balcon");
        checkBoxCocina = new JCheckBox("Cocina Integrada");
        
        add(checkBoxVista);
        add(checkBoxBalcon);
        add(checkBoxCocina);

        String[] opciones = {"estandar","suite","suite doble"};
        comboBox = new JComboBox<>(opciones);
        JTextArea textArea2 = new JTextArea("Tipo de habitacion");
        LabelTipoHabitacion labelTipoHabitacion = new LabelTipoHabitacion(textArea2, comboBox);
        add(labelTipoHabitacion);

        textFieldCamas =  new JTextField();
        LabelUbicacionCamas labelCamas = new LabelUbicacionCamas(new JTextArea("Numero de camas"),textFieldCamas);
        add(labelCamas);
        
        LabelBotones labelBotones = new LabelBotones(botonCerrar, botonCrear);
        add(labelBotones);


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
                String soloNiños = Boolean.toString(labelCamas.getCheckBoxNiños().isSelected());

                info.add(tamaño); info.add(cantidadPersonas); info.add(soloNiños);
                infoCamas.add(info);

            }

            controladorHabitaciones.crearHabitacion(ubicacion, balcon, vista, cocinaIntegrada, tipoHabitacion, infoCamas);
            setVisible(false);
            JOptionPane.showMessageDialog(rootPane, e, tipoHabitacion, numCamas, null);

        }
    }

    private class LabelUbicacionCamas extends JPanel {
        private LabelUbicacionCamas(JTextArea textArea, JTextField textField) {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT); textArea.setAlignmentY(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.RIGHT_ALIGNMENT); textField.setAlignmentY(Component.CENTER_ALIGNMENT);
            add(textArea);
            add(textField);
        }
    }

    private class LabelTipoHabitacion extends JPanel{
        private LabelTipoHabitacion(JTextArea textArea, JComboBox<String> comboBox){
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            comboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
            add(textArea);
            add(comboBox);
        }
    }
    private class LabelBotones extends JPanel{
        private LabelBotones(JButton cerrar, JButton crear){
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            cerrar.setAlignmentX(Component.LEFT_ALIGNMENT);
            crear.setAlignmentX(Component.RIGHT_ALIGNMENT);
            add(crear);
            add(cerrar);
        }
    }
    private class LabelCamas extends JPanel{
        private JComboBox<String> comboBox1;
        private JComboBox<String> comboBox2;
        private JCheckBox checkBoxNiños;

        private LabelCamas(JButton cerrar, int n ){


        String[] opciones1 = {"individual","doble","queen","king"};
        String[] opciones2 = {"1","2","3"};
        checkBoxNiños = new JCheckBox("Solo para niños");

        comboBox1 = new JComboBox<>(opciones1);
        comboBox2 = new JComboBox<>(opciones2);

        add(comboBox1); add(comboBox2); add(checkBoxNiños);

        JOptionPane.showMessageDialog(null, "Habitacion creada exitosamente");
    
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
