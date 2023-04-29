package InterfazDeUsuario;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import Modelo.ControladorHabitaciones;

public class ConsultarTarifas extends JDialog{

    public ConsultarTarifas(ControladorHabitaciones controladorHabitaciones){

        HashMap<String,ArrayList<Boolean>> mapTarifas = controladorHabitaciones.getMapTarifas();
        int index = 0;
        setLayout(new GridLayout(5,3));
        setTitle("Tarifas sin definir");

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        for(int i=0;i<12;i++){
            CalendarioPanel calendarioPanel= new CalendarioPanel(month,year,mapTarifas,index);

            add(calendarioPanel);

            index += calendarioPanel.getIteraciones();

            if(month == 12){
                month = 1;
                year ++;
            }
            else{
                month++;
            }
        }

        JTextArea individual = new JTextArea("Cyan: estandar"); individual.setEditable(false);
        JTextArea suite = new JTextArea("Rojo: suite"); suite.setEditable(false);
        JTextArea suiteDoble = new JTextArea("Rosado: suite doble"); suiteDoble.setEditable(false);

        add(individual);
        add(suite);
        add(suiteDoble);

        setSize(600, 800);
        setResizable(false);
    }
}
