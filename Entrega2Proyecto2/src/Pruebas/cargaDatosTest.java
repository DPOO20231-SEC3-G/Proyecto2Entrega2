package Pruebas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Modelo.ControladorHabitaciones;
import Modelo.ControladorHuespedes;
import Modelo.ControladorPagos;
import Modelo.ControladorReservas;
import Modelo.ControladorServicios;
import Modelo.FormasPago;
import Modelo.Habitacion;
import Modelo.Huesped;
import Modelo.ProductoRestaurante;
import Modelo.Reserva;
import Modelo.Servicio;
import Modelo.Tarjeta;

public class cargaDatosTest {
	
	ControladorHabitaciones cHabitaciones;
	
	ControladorHuespedes cHuespedes;
	
	ControladorPagos cPagos;
	
	ControladorServicios cServicios;
	
	ControladorReservas cReservas;
	
	@BeforeEach
	public void SetUp() {
		cHabitaciones = new ControladorHabitaciones();
		cHuespedes = new ControladorHuespedes();
		cPagos = new ControladorPagos();
		cServicios = new ControladorServicios();
		cReservas = new ControladorReservas();
		
	}
	
	@Test
	public void testCargaHabitaciones() throws NumberFormatException, IOException {
		cHabitaciones.cargarArchivoHabitaciones(new File("./Entrega2Proyecto2/DatosPruebas/Habitaciones.txt"), new File("./Entrega2Proyecto2/DatosPruebas/Camas.txt"));
		Habitacion hPrueba = cHabitaciones.getHabitacion(1);
		assertEquals(hPrueba.getUbicacion(),"102");
		assertTrue(hPrueba.isBalcon()); 
		assertFalse(hPrueba.isVista());
		assertTrue(hPrueba.isCocinaIntegrada());
		assertEquals(hPrueba.getTipoHabitacion(),"estandar");
		assertEquals(hPrueba.getEspacio(),5); 
		assertTrue(hPrueba.isAire());
		assertTrue(hPrueba.isCalefaccion());
		assertTrue(hPrueba.isTv());
		assertTrue(hPrueba.isCafetera());
		assertTrue(hPrueba.isRopaCama());
		assertTrue(hPrueba.isTapetes());
		assertTrue(hPrueba.isPlancha());
		assertTrue(hPrueba.isSecador());
		assertEquals(hPrueba.getVoltaje(),20);
		assertTrue(hPrueba.isUsbA());
		assertTrue(hPrueba.isUsbC());
		assertTrue(hPrueba.isDesayuno());
	}
	
	@Test
	public void testCargarTarifas() throws FileNotFoundException, IOException, ParseException {
		cHabitaciones.cargarArchivoHabitaciones(new File("./Entrega2Proyecto2/DatosPruebas/Habitaciones.txt"), new File("./Entrega2Proyecto2/DatosPruebas/Camas.txt"));
		cHabitaciones.cargarTarifas();
		Habitacion hPrueba = cHabitaciones.getHabitacion(1);
		SimpleDateFormat dFormater = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = dFormater.parse("01-04-2023");
		assertEquals(cHabitaciones.getPrecioDia(hPrueba,fecha),10000.0);
	}
	
	@Test
	public void testCargarHuespedes() throws FileNotFoundException, IOException {
		cHuespedes.cargarHuespedes();
		Huesped hPrueba = cHuespedes.getHuesped("Nicolas", 1092850219, "n.ruizp2@uniandes.edu.co", "3188490365", true);
		assertEquals(hPrueba.getNombre(),"Nicolas");
		assertEquals(hPrueba.getDocumento(),1092850219);
		assertEquals(hPrueba.getEmail(),"n.ruizp2@uniandes.edu.co");
		assertEquals(hPrueba.getCelular(),"3188490365");
		assertTrue(hPrueba.isNecesitaCama());
	}
	
	@Test
	public void testCargarMediosPago() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException {
		cPagos.cargarFormasPago();
		ArrayList<FormasPago> formasPagoPrueba = cPagos.getFormasDePago();
		for(FormasPago formasPago: formasPagoPrueba ) {
			assertNotEquals(formasPago.getNombre(),null);
		}
	}
	
	@Test
	public void testCargarTarjetas() throws NumberFormatException, IOException, ParseException {
		cPagos.cargarInfoTarjetas();
		HashMap<String,Tarjeta> map = cPagos.getInfo();
		assertTrue(map.containsKey("1234567891234567"));
		assertFalse(map.containsKey("1234567891234568"));
	}
	
	@Test
	public void testCargarServiciosYMenu() throws IOException {
		cServicios.cargarServiciosYMenu(new File("./Entrega2Proyecto2/DatosPruebas/Servicios.txt"), new File("./Entrega2Proyecto2/DatosPruebas/MenuRestaurante.txt"));
		ArrayList<Servicio> pServicios = cServicios.getServicios();
		ArrayList<ProductoRestaurante> pMenu = cServicios.getMenu();
		Servicio servicioPrueba = pServicios.get(0);
		assertEquals(servicioPrueba.getNombreServicio(),"Tour por la ciudad");
		assertEquals(servicioPrueba.getLugarServicio(),"Hotel");
		assertEquals(servicioPrueba.getTipoCobro(),"persona");
		assertEquals(servicioPrueba.getPrecio(),15000.0);
		ProductoRestaurante productoPrueba = pMenu.get(0);
		assertEquals(productoPrueba.getLugarServicio(),"Restaurante");
		assertEquals(productoPrueba.getNombreServicio(),"Desayuno buffet");
		assertEquals(productoPrueba.getPrecio(),45000);
		assertEquals(productoPrueba.getTipoCobro(),"persona");
	}
	
	@Test
	public void testCargarReservas() throws NumberFormatException, IOException, ParseException {
		cHabitaciones.cargarArchivoHabitaciones(new File("./Entrega2Proyecto2/DatosPruebas/Habitaciones.txt"), new File("./Entrega2Proyecto2/DatosPruebas/Camas.txt"));
		cHabitaciones.cargarTarifas();
		cHuespedes.cargarHuespedes();
		cServicios.cargarServiciosYMenu(new File("./Entrega2Proyecto2/DatosPruebas/Servicios.txt"), new File("./Entrega2Proyecto2/DatosPruebas/MenuRestaurante.txt"));
		cReservas.cargarReservas(cHabitaciones, cHuespedes, cServicios);
		Reserva reserva = cReservas.getReservaId(1);
		assertEquals(reserva.getHabitacion().getId(),10);
	}

}
