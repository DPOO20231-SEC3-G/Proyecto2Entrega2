package Pruebas;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReservaIntegrationTest {

    private Reserva reserva;
    private RangoFechas rangoFechas;
    private ArrayList<Huesped> huespedes;
    private Habitacion habitacion;


    @BeforeEach
    public void setup() {

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			rangoFechas = new RangoFechas(sdf.parse("2023-04-01"), sdf.parse("2023-04-03"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        habitacion = new Habitacion(1, "Ubicación", true, true, true, "Tipo de habitación",
                                    50.5f, true, true, true, true, true, true, true,
                                    true, 220, true, true, true);
        huespedes = new ArrayList<>();
        huespedes.add(new Huesped("Nombre", 1234567890, "email@example.com", "1234567890", true));

        reserva = new Reserva(rangoFechas, huespedes, habitacion, 1, false);
    }

    @Test
    public void testGetHuespedes() {
        ArrayList<Huesped> result = reserva.getHuespedes();
        Assertions.assertEquals(huespedes, result);
    }

    @Test
    public void testGetRangoFecha() {
        String expected = "2023/04/01 - 2023/04/03";
        String result = reserva.getRangoFecha();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetHabitacion() {
        Habitacion result = reserva.getHabitacion();
        Assertions.assertEquals(habitacion, result);
    }

    @Test
    public void testGetIdReserva() {
        int result = reserva.getIdReserva();
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetProductoMenuConsumido() {
        ArrayList<ProductoRestaurante> result = reserva.getProductoMenuConsumido();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetServiciosConsumidos() {
        ArrayList<Servicio> result = reserva.getServiciosConsumidos();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testIsCancelado() {
        boolean result = reserva.isCancelado();
        Assertions.assertFalse(result);
    }

    @Test
    public void testSetCancelado() {
        reserva.setCancelado(true);
        boolean result = reserva.isCancelado();
        Assertions.assertTrue(result);
    }

    @Test
    public void testAddProductoRestaurante() {
        ProductoRestaurante producto = new ProductoRestaurante("Buffet", "Individual",
                                                                "Restaurante", 10.5, "10:50-16:50",
                                                                "Plato", 5);
        reserva.addProductoRestaurante(producto);

        ArrayList<ProductoRestaurante> result = reserva.getProductoMenuConsumido();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(producto, result.get(0));
    }

    @Test
    public void testAddServicio() {
        Servicio servicio = new Servicio("Spa", "Individual", "Spa", 20.75, 3);
        reserva.addServicio(servicio);

        ArrayList<Servicio> result = reserva.getServiciosConsumidos();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(servicio, result.get(0));
    }
}