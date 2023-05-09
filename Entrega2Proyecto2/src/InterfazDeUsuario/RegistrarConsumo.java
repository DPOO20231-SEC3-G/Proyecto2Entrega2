package InterfazDeUsuario;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Modelo.ControladorReservas;
import Modelo.ControladorServicios;
import Modelo.Hotel;
import Modelo.ProductoRestaurante;
import Modelo.Reserva;
import Modelo.Servicio;

public class RegistrarConsumo extends JFrame implements ActionListener{
    
    private JComboBox<String> comboReservas;
    private JComboBox<String> comboProductos;
    private JComboBox<String> comboServicios;
    private JComboBox<String> comboTipo;
    private JButton botoncerrar;
    private JButton botonconfirmar;
    private Reserva reservaSelected;
    private String[] listaProds;
    private String[] listaservs;
    private String[] listareservs;
    private Hotel hotel;
    private String nameServicio;
    private String nameClass;

    public Hotel getHotel() {
        return hotel;
    }
    public String getNameClass() {
        return nameClass;
    }
    public String getNameServicio() {
        return nameServicio;
    }
    public void setReservaSelected(Reserva reserva) {
        reservaSelected = reserva ;
    }
    public Reserva getReservaSelected() {
        return reservaSelected ;
    }
    public String[] getListaProds() {
        return listaProds;
    }
    public String[] getListaservs() {
        return listaservs;
    }
    public String[] getListareservs(){
        return listareservs;
    }

    public RegistrarConsumo(Hotel hotel){
        this.hotel = hotel;
        ControladorReservas controladorReservas = hotel.getControladorReservas();
        ControladorServicios controladorServicios = hotel.getControladorServicios();

        setLayout(new GridLayout(4,2));
        setTitle("Agregar consumo");
        setSize(300, 300);

        String[] listaservs = showServicios(controladorServicios);
        String[] listaProds = showProductos(controladorServicios);
        String[] listareservs = showReservas(controladorReservas);

        comboProductos = new JComboBox<String>(listaProds);
        comboProductos.addActionListener(this);
        comboReservas = new JComboBox<String>(listareservs);
        comboReservas.addActionListener(this);
        comboServicios = new JComboBox<String>(listaservs);
        comboServicios.addActionListener(this);

        String[] options = {"Servicio","Producto"};
        comboTipo = new JComboBox<String>(options);
        comboTipo.addActionListener(this);

        botoncerrar = new JButton("Cerrar");
        botonconfirmar = new JButton("Confirmar");
        botoncerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        botoncerrar.addActionListener(this);
        botonconfirmar.addActionListener(this);

        JTextArea textArea = new JTextArea("Tipo: "); textArea.setFont(new Font("Arial", Font.BOLD,12)); textArea.setEditable(false);
        JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

        add(comboReservas);add(hueco);
        add(textArea);add(comboTipo);
        add(comboProductos);add(hueco);
        add(comboServicios);add(hueco);
        add(botonconfirmar);add(botoncerrar);

        setVisible(true);
        comboProductos.setVisible(false);
        comboServicios.setVisible(false);

    }

    public String[] showServicios(ControladorServicios controladorServicios){
        ArrayList<Servicio> listaServicios = controladorServicios.getServicios();
        String[] list = new String[listaServicios.size()] ;
        for (int i=0;i< listaServicios.size();i++){
            list[i]=listaServicios.get(i).getNombreServicio();
        }
        return list;
    }

    public String[] showProductos(ControladorServicios controladorServicios){
        ArrayList<ProductoRestaurante> listaproductos = controladorServicios.getMenu();
        String[] list = new String[listaproductos.size()] ;
        for (int i=0;i< listaproductos.size();i++){
            list[i]=listaproductos.get(i).getNombreServicio();
        }
        return list;
    }

    public String[] showReservas(ControladorReservas controladorReservas){
        ArrayList<Reserva> listareservas = controladorReservas.getReservas();
        String[] list = new String[listareservas.size()] ;
        for (int i=0;i< listareservas.size();i++){
            list[i] = String.valueOf(i+1);
        }
        return list;
    }
    

    public void actionPerformed(ActionEvent e) {

        ControladorReservas controladorReservas = getHotel().getControladorReservas();
        ControladorServicios controladorServicios = getHotel().getControladorServicios();

        if(e.getSource() == botoncerrar){
            setVisible(false);
        }

        if(e.getSource().equals(comboTipo)){
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBox1 = (JComboBox<String>) e.getSource(); 
            if("Servicio" == comboBox1.getSelectedItem()){
                comboServicios.setVisible(true);
                comboProductos.setVisible(false);
                nameClass = "Servicio";
            }
            else if ("Producto" == comboBox1.getSelectedItem()){
                comboProductos.setVisible(true);
                comboServicios.setVisible(false);
                nameClass = "ProductoRestaurante";
            }
        }

        if(e.getSource().equals(comboReservas)){
            @SuppressWarnings("unchecked")
            JComboBox<String> comboR = (JComboBox<String>) e.getSource();
            int ID = Integer.parseInt((String) comboR.getSelectedItem());
            ArrayList<Reserva> reservas = controladorReservas.getReservas();
            reservaSelected = reservas.get(ID-1);
        }

        if(e.getSource().equals(comboProductos)){
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBoxP = (JComboBox<String>) e.getSource();
            nameServicio = (String) comboBoxP.getSelectedItem();
        }

        if(e.getSource().equals(comboServicios)){

            @SuppressWarnings("unchecked")
            JComboBox<String> comboBoxS = (JComboBox<String>) e.getSource();
            nameServicio = (String) comboBoxS.getSelectedItem();
        }

        if(e.getSource().equals(botonconfirmar)){
            Reserva reserva = getReservaSelected();
            if ("Servicio".equals(getNameClass())){
                int ID = controladorServicios.getIdServicio(nameClass) ;
                Servicio sevricio = controladorServicios.getServicioId(ID);
                reserva.addServicio(sevricio);   
                hotel.cargarServicioConsumido(reserva); 
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Consumo de servicio agregado exitosamente");            
            }
            else if("ProductoRestaurante".equals(nameClass)){
                int ID = controladorServicios.getIdMenu(nameClass) ;
                ProductoRestaurante producto = controladorServicios.getMenuId(ID);
                reserva.addServicio(producto);
                hotel.cargarServicioConsumido(reserva);
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Consumo de producto agregado exitosamente");
            }
        }

        
    }

}
