package InterfazDeUsuario;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JDialog;

import Modelo.ControladorHabitaciones;

public class ConsultarTarifas extends JDialog{

    public ConsultarTarifas(ControladorHabitaciones controladorHabitaciones){

        HashMap<String,ArrayList<Boolean>> mapTarifas = controladorHabitaciones.getMapTarifas();
        int index = 0;
        setLayout(new GridLayout(4,3));

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        for(int i=0;i<12;i++){
            CalendarioPanel calendarioPanel= new CalendarioPanel(month,year,mapTarifas,index);

            add(calendarioPanel);

            index = calendarioPanel.getIndex() + 1;

            if(month == 12){
                month = 1;
                year ++;
            }
            else{
                month++;
            }
        }

        setSize(500, 700);
        setResizable(false);
    }
}
