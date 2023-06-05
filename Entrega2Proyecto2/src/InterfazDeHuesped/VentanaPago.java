package InterfazDeHuesped;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import Modelo.ControladorHabitaciones;
import Modelo.ControladorPagos;
import Modelo.ControladorReservas;
import Modelo.FormasPago;
import Modelo.Hotel;
import Modelo.Reserva;

public class VentanaPago extends JDialog implements ActionListener {
	
	private RealizarPago realizarPago;
	private JLabel lNumero;
	private JLabel lVerificacion;
	private JLabel lVencimiento;
	private JLabel costo;
	private JTextArea tNumero;
	private JTextArea tVerificacion;
	private JTextArea tVencimiento;
	private JLabel precio;
	private JButton bPagar;
	private JButton bActualizar;
	private JPanel gridPanel;
	private JPanel panel;
	private ControladorPagos cPagos;
	private Hotel hotel;
	private ArrayList<String> metodosPagoString;
	private ArrayList<JRadioButton> radioButtons;
	private JPanel radioPanel;
	private ControladorReservas cReservas;
	private long valorAPagar;
	private ControladorHabitaciones cHabitaciones;
	private ButtonGroup group;
	
	
	
	public VentanaPago(RealizarPago nRealizarPago) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException, ParseException {
		realizarPago = nRealizarPago;
		hotel = realizarPago.getHotel();
		cPagos = hotel.getControladorPagos();
		cPagos.cargarFormasPago();
		cPagos.cargarInfoTarjetas();
		cReservas = hotel.getControladorReservas();
		cHabitaciones = hotel.getControladorHabitaciones();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		panel = new JPanel();

        setLayout(new BorderLayout());

        setSize(600,200);
        
        setResizable(false);
        
        gridPanel = new JPanel(new GridLayout(5,2));
        
        lNumero = new JLabel("Numero de tarjeta: ");
        tNumero = new JTextArea();
        
        gridPanel.add(lNumero);
        gridPanel.add(tNumero);
        
        lVencimiento = new JLabel("Fecha de vencimiento en formato dd/MM/yyyy: ");
        tVencimiento = new JTextArea();
        
        gridPanel.add(lVencimiento);
        gridPanel.add(tVencimiento);
        
        lVerificacion = new JLabel("Codigo de verificacion: ");
        tVerificacion = new JTextArea();
        
        gridPanel.add(lVerificacion);
        gridPanel.add(tVerificacion);
        
        costo = new JLabel("Costo a pagar: ");
        Reserva reserva = cReservas.getReservaId(Integer.parseInt(realizarPago.getId()));
        valorAPagar = cReservas.precioReserva(reserva, cHabitaciones);
        precio = new JLabel(String.valueOf(valorAPagar));
        
        gridPanel.add(costo);
        gridPanel.add(precio);
        
        bPagar = new JButton("Pagar");
        bPagar.addActionListener(this);
        bActualizar = new JButton("Actualizar medios de pago");
        bActualizar.addActionListener(this);
        
        gridPanel.add(bPagar);
        gridPanel.add(bActualizar);
        
        setLayout(new BorderLayout());
        
        add(gridPanel, BorderLayout.CENTER);
        
        metodosPagoString = new ArrayList<String>();
        
        radioPanel = new JPanel();
        
        radioButtons = new ArrayList<JRadioButton>();
        
        group = new ButtonGroup();
        
        for(FormasPago metodo: cPagos.getFormasDePago()) {
        	metodosPagoString.add(metodo.getNombre());
        	JRadioButton rBOpciones = new JRadioButton(metodo.getNombre());
        	radioButtons.add(rBOpciones);
        	radioPanel.add(rBOpciones);
        	group.add(rBOpciones);
        }
        
        add(radioPanel, BorderLayout.NORTH);
        
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bActualizar) {
			radioPanel.removeAll();
			radioButtons.clear();
		
		try {
			cPagos.cargarFormasPago();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		metodosPagoString = new ArrayList<String>();
		
		group = new ButtonGroup();
		
		for(FormasPago fpago: cPagos.getFormasDePago()) {
			JRadioButton radioButton = new JRadioButton (fpago.getNombre());
			metodosPagoString.add(fpago.getNombre());
			radioButtons.add(radioButton);
			radioPanel.add(radioButton);
			group.add(radioButton);
		}
		
		radioPanel.revalidate();
		radioPanel.repaint();
		}else if(e.getSource() == bPagar){
			ButtonModel selectedModel = group.getSelection();
	        JRadioButton selectedButton = null;
	        for (JRadioButton radioButton : radioButtons) {
	            if (radioButton.getModel() == selectedModel) {
	                selectedButton = radioButton;
	                break;
	            }
	        }
		if(selectedButton != null) {
			String selectedText = selectedButton.getText();
			FormasPago formaPagoElegida = null;
			for (FormasPago formaPago : cPagos.getFormasDePago()) {
				if (selectedText.equals(formaPago.getNombre())) {
					formaPagoElegida = formaPago;
				}
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha= new Date();
			try {
				fecha = formatter.parse(tVencimiento.getText());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String rta =formaPagoElegida.realizarPago(tNumero.getText(), fecha , tVerificacion.getText(), valorAPagar, cPagos.getInfo());
			JOptionPane.showMessageDialog(null,rta, "Alerta", JOptionPane.INFORMATION_MESSAGE );
			try {
				cPagos.guardarInfoTarjetas();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		}
	}

}
