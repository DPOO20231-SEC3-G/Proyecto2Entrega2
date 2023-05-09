package InterfazDeUsuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JTextArea;
import Modelo.ControladorServicios;
import Modelo.ProductoRestaurante;
import Modelo.Servicio;


public class ModificarTarifaServicios extends JDialog implements ActionListener {
    
    private JButton botoncerrar;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboServicios;
    private JComboBox<String> comboMenu;
    private ControladorServicios controladorServicios;

    public ModificarTarifaServicios(ControladorServicios controladorServicios){

        this.controladorServicios = controladorServicios;


        setLayout(new GridLayout(3,2));
        setTitle("Modificar tarifa producto o servicio");
        setSize(400, 300);

        botoncerrar = new JButton("Cerrar");
        botoncerrar.addActionListener(this);
        botoncerrar.setAlignmentX(Component.RIGHT_ALIGNMENT);

        String[] options = {"Servicios", "Productos Menú"};
        comboBox= new JComboBox<String>(options);
        comboBox.addActionListener(this);

        JTextArea textArea1 = new JTextArea("Tipo de servicio a modificar: "); textArea1.setFont(new Font("Arial", Font.BOLD,12)); textArea1.setEditable(false);
    
        String[] list1 = showProductos(controladorServicios) ;
        comboMenu = new JComboBox<String>(list1);
        comboMenu.addActionListener(this);

        String[] list2 = showServicios(controladorServicios);
        comboServicios = new JComboBox<String>(list2);
        comboServicios.addActionListener(this);

        
        JTextArea hueco = new JTextArea(""); hueco.setEditable(false);

        add(textArea1);add(comboBox);
        add(comboServicios);add(hueco);
        add(comboMenu);add(hueco);
        add(botoncerrar);

        setVisible(true);
        comboMenu.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botoncerrar){
            setVisible(false);
        }

        else if(e.getSource().equals(comboBox)){
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBox1 = (JComboBox<String>) e.getSource(); 
            if("Servicio".equals(comboBox1.getSelectedItem())){
                comboServicios.setVisible(true);
                comboMenu.setVisible(false);
            }
            else if ("Productos Menú".equals(comboBox1.getSelectedItem())){
                comboMenu.setVisible(true);
                comboServicios.setVisible(false);
            }
        }

        else if(e.getSource().equals(comboMenu)){
            String classServicio = "ProductoRestaurante";
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBoxM = (JComboBox<String>) e.getSource();
            String nameServicio = (String) comboBoxM.getSelectedItem();
            new LabelPrecio(botoncerrar, nameServicio, classServicio, controladorServicios);
        }

        else if(e.getSource().equals(comboServicios)){
            String classServicio = "Servicio";
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBoxS = (JComboBox<String>) e.getSource();
            String nameServicio = (String) comboBoxS.getSelectedItem();
            new LabelPrecio(botoncerrar, nameServicio, classServicio, controladorServicios);
        }
    }

    private class LabelPrecio extends JPanel{
        public LabelPrecio(JButton botoncerrar, String nameServicio, String classServicio, ControladorServicios controladorServicios){
            setLayout(new GridLayout(2, 2));
            setSize(200,200);
            
            JFrame jframe = new JFrame();

            double precio = Double.parseDouble(JOptionPane.showInputDialog(jframe, "Precio:"));

            setVisible(true);

            if("Servicio".equals(classServicio)){
                int ID = controladorServicios.getIdServicio(nameServicio);
                Servicio obj = controladorServicios.getServicioId(ID);
                obj.setPrecio(precio);
                controladorServicios.cambiarPrecio("Servicios",nameServicio,precio);
                JOptionPane.showMessageDialog(null, "Servicio modificado exitosamente");
            }

            else if ("ProductoRestaurante".equals(classServicio)){
                int ID = controladorServicios.getIdMenu(nameServicio);
                ProductoRestaurante obj = controladorServicios.getMenuId(ID);
                obj.setPrecio(precio);
                controladorServicios.cambiarPrecio("MenuRestaurante",nameServicio,precio);
                JOptionPane.showMessageDialog(null, "Producto modificado exitosamente");
            }
        }
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
}

    
