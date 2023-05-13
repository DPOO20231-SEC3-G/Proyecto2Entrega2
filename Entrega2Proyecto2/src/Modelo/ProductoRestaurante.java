package Modelo;

public class ProductoRestaurante extends Servicio{

    public String rangoHoras;
    public String tipoProducto;
    public ProductoRestaurante(String nombreServicio, String tipoCobro, String lugarServicio,
            double precio, String rangoHoras, String tipoProducto, int ventas) {
        super(nombreServicio, tipoCobro, lugarServicio, precio, ventas);
        this.rangoHoras = rangoHoras;
        this.tipoProducto = tipoProducto;
    }


}
