package InterfazDeUsuario;

import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JDialog;

public class ConsultarTarifas extends JDialog{

    public ConsultarTarifas(){
        setLayout(new GridLayout(4,3));

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        for(int i=0;i<12;i++){
            add(new CalendarioPanel(month,year));

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
