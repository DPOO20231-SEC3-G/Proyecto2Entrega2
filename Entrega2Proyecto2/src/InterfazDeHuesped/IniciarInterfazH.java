package InterfazDeHuesped;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.swing.*;

import Modelo.Hotel;
import Modelo.Usuario;

public class IniciarInterfazH extends JFrame implements ActionListener{
	private Hotel hotel;
	private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
	private JButton newUserButton;

	private Usuario user;


	public static void main(String[] args)  {
		
		new IniciarInterfazH(true,null);
	}
	public IniciarInterfazH(Boolean mostrar,Usuario user) {
		this.hotel = new Hotel();

		try {
			hotel.cargarArchivoHabitaciones();
			hotel.cargarUsuarios();
			hotel.getControladorPagos().cargarFormasPago();
			hotel.getControladorPagos().cargarInfoTarjetas();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setTitle("Iniciar Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(3, 3));
		JTextArea b = new JTextArea("Bienvenido al sistema de"); b.setEditable(false);
		JTextArea a = new JTextArea("reservas para huéspedes"); a.setEditable(false);
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

		newUserButton = new JButton("Crear Usuario");
		newUserButton.addActionListener(this);

        add(inputPanel, BorderLayout.CENTER);
		add(loginButton,BorderLayout.SOUTH);
		add(newUserButton,BorderLayout.NORTH);


        setSize(300, 150);
        setLocationRelativeTo(null); 
        setVisible(mostrar);	
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String usuario = userField.getText();
            String contraseña = new String(passField.getPassword());
            
			user = hotel.getUsuario(usuario, contraseña);
			if( user != null && user.getRol() == "Huesped"){ 
				InterfazHuesped InterfazHuesped = new InterfazHuesped(hotel);
				setVisible(false);
				InterfazHuesped.setVisible(true);}
			else{
				JOptionPane.showMessageDialog(this,"No se encontro el usuario");}
			}

		else if (e.getSource() == newUserButton){
			new CrearUsuario(hotel);
		}
		
    }
    

	public Hotel getHotel() {
		return hotel;
	}

}
