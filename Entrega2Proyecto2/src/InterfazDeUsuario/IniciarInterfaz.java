package InterfazDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Modelo.Hotel;
import Modelo.Usuario;

public class IniciarInterfaz extends JFrame implements ActionListener{
	private Hotel hotel;
	private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

	private Usuario user;


	public static void main(String[] args) {
		
		new IniciarInterfaz(true,null);
	}
	public IniciarInterfaz(Boolean mostrar,Usuario user){
		this.hotel = new Hotel();

		try {
			hotel.cargarArchivoHabitaciones();
			hotel.cargarUsuarios();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle("Iniciar Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(3, 3));
		JTextArea b = new JTextArea("Bienvenido al sistema de"); b.setEditable(false);
		JTextArea a = new JTextArea("administracion de hoteles"); a.setEditable(false);
		inputPanel.add(b);
		inputPanel.add(a);
        inputPanel.add(new JLabel("Usuario:"));
        userField = new JTextField();
        inputPanel.add(userField);
        inputPanel.add(new JLabel("Contraseña:"));
        passField = new JPasswordField();
        inputPanel.add(passField);

        loginButton = new JButton("Iniciar Sesion");
        loginButton.addActionListener(this);


        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);


        setSize(300, 150);
        setLocationRelativeTo(null); 
        setVisible(mostrar);

		if(mostrar == false){
			admin(hotel, user);
		}
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String usuario = userField.getText();
            String contraseña = new String(passField.getPassword());
            
			user = hotel.getUsuario(usuario, contraseña);
			if( user != null){
				if(user.getRol().equals("Administrador")){
					admin(hotel,user);
				}
				else if(user.getRol().equals("Empleado")){
					new InterfazEmpleado(hotel, user);
					setVisible(false);
				}
				else if(user.getRol().equals("Recepcionista")){
					new InterfazRecepcionista(hotel, user);
					setVisible(false);
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"No se encontro el usuario");
			}
        }
    }

	public void admin(Hotel hotel, Usuario user) {
		new InterfazAdministrador(hotel,user);
		setVisible(false);
	}
	public Hotel getHotel() {
		return hotel;
	}

}
