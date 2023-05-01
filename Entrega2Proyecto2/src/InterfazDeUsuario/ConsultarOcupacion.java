package InterfazDeUsuario;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JDialog;

import Modelo.ControladorHabitaciones;

public class ConsultarOcupacion extends JDialog{

    public ConsultarOcupacion(ControladorHabitaciones controladorHabitaciones){
        setTitle("Diagrama de ocupacion");
        setLayout(new GridLayout(4,4));
        int index = 0;
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        ArrayList<Color> colores = controladorHabitaciones.getListOcupacion();
        for(int i=0;i<12;i++){
            CalendarioPanel calendarioPanel= new CalendarioPanel(month,year,null,index,false,colores);

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

        add(new PanelColorMensaje(Color.decode("#EFFBEF"),"0-24.9%"));
        add(new PanelColorMensaje(Color.decode("#A9F5A9"),"25-49.9%"));
        add(new PanelColorMensaje(Color.decode("#00FF00"),"50-74.9%"));
        add(new PanelColorMensaje(Color.decode("#298A08"),"75-100%"));

        setSize(600, 800);
        setResizable(false);

    }
    
}
