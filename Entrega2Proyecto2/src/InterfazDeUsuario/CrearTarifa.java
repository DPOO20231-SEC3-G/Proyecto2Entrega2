package InterfazDeUsuario;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Modelo.ControladorHabitaciones;

public class CrearTarifa extends JDialog implements ActionListener{

    private JComboBox<String> comboBox;
    private JCheckBox lunes;
    private JCheckBox martes;
    private JCheckBox miercoles;
    private JCheckBox jueves;
    private JCheckBox viernes;
    private JCheckBox sabado;
    private JCheckBox domingo;
    private JTextField valor;
    private JTextField fechaInicial;
    private JTextField fechaFinal;

    private JButton crear;
    private JButton cerrar;

    private ControladorHabitaciones controladorHabitaciones;

    
    public CrearTarifa(ControladorHabitaciones controladorHabitaciones){
        this.controladorHabitaciones = controladorHabitaciones;

        setLayout(new GridLayout(9,2));
        setTitle("Crear Tarifa");
        setSize(400, 300);


        String[] opciones = {"estandar","suite","suite doble"};
        comboBox = new JComboBox<>(opciones);
        valor = new JTextField();
        fechaInicial = new JTextField();
        fechaFinal = new JTextField();

        lunes = new JCheckBox("Lunes");
        martes = new JCheckBox("martes");
        miercoles = new JCheckBox("miercoles");
        jueves = new JCheckBox("jueves");
        viernes = new JCheckBox("viernes");
        sabado = new JCheckBox("sabado");
        domingo = new JCheckBox("domingo");

        JTextArea hab = new JTextArea("Tipo Habitacion"); hab.setEditable(false);
        JTextArea tar = new JTextArea("Valor Tarifa"); tar.setEditable(false);
        JTextArea in = new JTextArea("Fecha inicial (AAAA-MM-dd)"); in.setEditable(false);
        JTextArea fin = new JTextArea("Fecha final (AAAA-MM-dd)"); fin.setEditable(false);
        JTextArea owo = new JTextArea(""); owo.setEditable(false);
        add(hab); add(comboBox);
        add(tar); add(valor);
        add(in); add(fechaInicial);
        add(fin); add(fechaFinal);

        add(lunes); add(martes);
        add(miercoles); add(jueves);
        add(viernes); add(sabado);
        add(domingo); add(owo);

        crear = new JButton("Crear Tarifa"); crear.addActionListener(this);
        cerrar = new JButton("Cancelar"); cerrar.addActionListener(this);

        add(crear);
        add(cerrar);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cerrar){
            setVisible(false);
        }
        else if(e.getSource() == crear){
            String diasSemana = "";
            if(lunes.isSelected()){
                diasSemana += "L";
            }
            if(martes.isSelected()){
                diasSemana += "M";
            }
            if(miercoles.isSelected()){
                diasSemana += "X";
            }
            if(jueves.isSelected()){
                diasSemana += "J";
            }
            if(viernes.isSelected()){
                diasSemana += "V";
            }
            if(sabado.isSelected()){
                diasSemana += "S";
            }
            if(domingo.isSelected()){
                diasSemana += "D";
            }
            String tipoHabitacion = comboBox.getItemAt(comboBox.getSelectedIndex());
            double valorTarifa = Double.parseDouble(valor.getText());
            
            try {
                controladorHabitaciones.cargarTarifaServicio(tipoHabitacion, valorTarifa, fechaInicial.getText(), fechaFinal.getText(), diasSemana);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            setVisible(false);
            JOptionPane.showMessageDialog(null,"La tarifa se creó exitosamente");

        }
    }
}
