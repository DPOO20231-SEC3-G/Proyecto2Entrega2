package InterfazDeUsuario;

import javax.swing.*;

import Modelo.Hotel;
import Modelo.Usuario;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class InterfazEmpleado extends JFrame implements ActionListener {
    private JButton botonRegistrarConsumo;
    private JButton cambiarUsuario;

    private Hotel hotel;

    public InterfazEmpleado(Hotel hotel, Usuario user) {

        this.hotel = hotel;

        setTitle("Bienvenid@: " +user.getRol() + "  "+ user.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        

        botonRegistrarConsumo = new JButton("Registrar consumo"); 
        botonRegistrarConsumo.addActionListener(this);
        cambiarUsuario = new JButton("Cambiar de usuario"); 
        cambiarUsuario.addActionListener(this);
        
        panel.add(botonRegistrarConsumo);
        panel.add(cambiarUsuario);

        for (Component component : panel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setHorizontalAlignment(SwingConstants.CENTER);
                ((JButton) component).setVerticalAlignment(SwingConstants.CENTER);
                ((JButton) component).setPreferredSize(new Dimension(250, 100));
            }
        }
        
        add(panel);
        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrarConsumo) {
            RegistrarConsumo registrarConsumo = new RegistrarConsumo(hotel);
            registrarConsumo.setVisible(true);
        } else if(e.getSource() == cambiarUsuario){
            setVisible(false);
            new IniciarInterfaz(true,null);
        }
        
    }

    public JButton getBotonRegistrarConsumo() {
        return botonRegistrarConsumo;
    }

    public Hotel getHotel() {
        return hotel;
    }
}
