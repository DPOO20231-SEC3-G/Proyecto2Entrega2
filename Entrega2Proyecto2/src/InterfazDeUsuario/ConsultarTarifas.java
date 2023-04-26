package InterfazDeUsuario;


import javax.swing.JDialog;


public class ConsultarTarifas extends JDialog{

    public ConsultarTarifas(){
        add(new CalendarioPanel());


        setSize(500, 200);
    }
    
}
