package Modelo;

public class Servicio {
    private String nombreServicio;
    private String tipoCobro;
    private String lugarServicio;
    private double precio;
    private boolean pagado = false;

    private int ventas;

    public Servicio(String nombreServicio, String tipoCobro, String lugarServicio, double precio, int ventas) {
        this.nombreServicio = nombreServicio;
        this.tipoCobro = tipoCobro;
        this.lugarServicio = lugarServicio;
        this.precio = precio;

        this.ventas = ventas;
    }
    public String getNombreServicio() {
        return nombreServicio;
    }
    public String getTipoCobro() {
        return tipoCobro;
    }
    
    public String getLugarServicio() {
        return lugarServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
    public int getVentas() {
        return ventas;
    }

    

}
