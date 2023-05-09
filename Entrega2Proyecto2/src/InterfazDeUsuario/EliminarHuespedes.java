package InterfazDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EliminarHuespedes extends JDialog implements ActionListener {

    CrearReserva crearReserva;

    GenerarLog generarLog;

    int value;
    
    JList<String> lista;

    JScrollPane scroll;
    
    DefaultListModel<String> model;

    ArrayList<ArrayList<String>> listaHuespedes;

    JLabel lDocumento;

    JTextArea tADocumento;

    JButton bEliminar;

    public EliminarHuespedes(CrearReserva nCrearReserva){

        crearReserva = nCrearReserva;

        value = 1;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        setSize(500,300);
        setResizable(false);
        model = new DefaultListModel<String>();
        
        listaHuespedes = crearReserva.getHuespedes();

        for (ArrayList<String> info : listaHuespedes) {
            String cadena= "";
            cadena += "Nombre: "+info.get(0);
            cadena += ", Documento: "+info.get(1);
            cadena += ", Email: "+info.get(2);
            cadena += ", Celular: "+info.get(3);
            cadena += ", Necesita Cama: "+info.get(4);
            model.addElement(cadena);
        }

        lista = new JList<String>(model);
        scroll = new JScrollPane();

        scroll.setViewportView(lista);

        scroll.setSize(250, 300);
        add(scroll, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(5,1));

        JLabel vacio1 = new JLabel();
        panelDerecho.add(vacio1);

        lDocumento = new JLabel("Ingrese el documento del huesped a eliminar");
        panelDerecho.add(lDocumento);

        tADocumento = new JTextArea();
        panelDerecho.add(tADocumento);

        bEliminar = new JButton("Eliminar huesped");
        bEliminar.addActionListener(this);
        panelDerecho.add(bEliminar);

        JLabel vacio2 = new JLabel();
        panelDerecho.add(vacio2);

        add(panelDerecho, BorderLayout.EAST);

        setVisible(true);

    }

    public EliminarHuespedes(GenerarLog nGenerarLog){

        generarLog = nGenerarLog;

        value = 0;

        setLayout(new BorderLayout());
        setSize(500,300);
        setResizable(false);
        model = new DefaultListModel<String>();
        
        listaHuespedes = generarLog.getHuespedes();

        for (ArrayList<String> info : listaHuespedes) {
            String cadena= "";
            cadena += "Nombre: "+info.get(0);
            cadena += ", Documento: "+info.get(1);
            cadena += ", Email: "+info.get(2);
            cadena += ", Celular: "+info.get(3);
            cadena += ", Necesita Cama: "+info.get(4);
            model.addElement(cadena);
        }

        JList<String> lista = new JList<String>(model);
        scroll = new JScrollPane();

        scroll.setViewportView(lista);

        scroll.setSize(250, 300);
        add(scroll, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridLayout(5,1));

        JLabel vacio1 = new JLabel();
        panelDerecho.add(vacio1);

        lDocumento = new JLabel("Ingrese el documento del huesped a eliminar");
        panelDerecho.add(lDocumento);

        tADocumento = new JTextArea();
        panelDerecho.add(tADocumento);

        bEliminar = new JButton("Eliminar huesped");
        bEliminar.addActionListener(this);
        panelDerecho.add(bEliminar);

        JLabel vacio2 = new JLabel();
        panelDerecho.add(vacio2);

        add(panelDerecho, BorderLayout.EAST);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == bEliminar){
            boolean encontrado = false;
            int i = 0;
            while((encontrado == false) && (i < listaHuespedes.size())){
                if (listaHuespedes.get(i).get(1).equals(tADocumento.getText()) && value == 1){
                    encontrado = true;
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                    // El usuario eligió "Sí"
                        listaHuespedes.remove(i);
                        crearReserva.setHuespedes(listaHuespedes);
                        crearReserva.setnHuespedes(crearReserva.getnHuespedes()-1);
                        crearReserva.actualizarNHuespedes();
                        EliminarHuespedes.this.dispose();
                    } else {
                    // El usuario eligió "No" o cerró la ventana
                    }
                }else if(listaHuespedes.get(i).get(1).equals(tADocumento.getText()) && value == 0){
                    encontrado = true;
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                    // El usuario eligió "Sí"
                        listaHuespedes.remove(i);
                        generarLog.setHuespedes(listaHuespedes);
                        EliminarHuespedes.this.dispose();
                }else {}
            }
                i++; 
            }
            JOptionPane.showMessageDialog(null,"Verifique el documento del huesped a eliminar", "Alerta", JOptionPane.INFORMATION_MESSAGE );
        }
    }


    
}
