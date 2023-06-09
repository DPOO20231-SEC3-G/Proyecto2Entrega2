package InterfazDeUsuario;

import java.awt.Color;
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
        setLayout(new GridLayout(5,3));
        setTitle("Tarifas sin definir");

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        for(int i=0;i<12;i++){
            CalendarioPanel calendarioPanel= new CalendarioPanel(month,year,mapTarifas,index,true,null);

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

        add(new PanelColorMensaje(Color.CYAN, "Estandar"));
        add(new PanelColorMensaje(Color.RED, "Suite"));
        add(new PanelColorMensaje(Color.PINK, "Suite Doble"));

        setSize(600, 800);
        setResizable(false);
    }
}
