package Modelo;

import java.util.ArrayList;

public class Habitacion {
    private int id;
    private String ubicacion;
    private boolean balcon;
    private boolean vista;
    private boolean cocinaIntegrada;
    private String tipoHabitacion;

    private ArrayList<Cama> camas;
    private ArrayList<Reserva> reservas;

    //Proyecto 3

    private float tamañoMts;
    private boolean aire;
    private boolean calefaccion;
    private boolean tv;
    private boolean cafetera;
    private boolean ropaCama;
    private boolean tapetes;
    private boolean plancha;
    private boolean secador;
    private int voltaje;
    private boolean usbA;
    private boolean usbC;
    private boolean desayuno;

    
    public Habitacion(int id, String ubicacion, boolean balcon, boolean vista, boolean cocinaIntegrada,
            String tipoHabitacion, float tamañoMts, boolean aire,
            boolean calefaccion, boolean tv, boolean cafetera, boolean ropaCama, boolean tapetes, boolean plancha,
            boolean secador, int voltaje, boolean usbA, boolean usbC, boolean desayuno) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.balcon = balcon;
        this.vista = vista;
        this.cocinaIntegrada = cocinaIntegrada;
        this.tipoHabitacion = tipoHabitacion;
        this.camas = new ArrayList<Cama>();
        this.reservas =  new ArrayList<Reserva>();
        this.tamañoMts = tamañoMts;
        this.aire = aire;
        this.calefaccion = calefaccion;
        this.tv = tv;
        this.cafetera = cafetera;
        this.ropaCama = ropaCama;
        this.tapetes = tapetes;
        this.plancha = plancha;
        this.secador = secador;
        this.voltaje = voltaje;
        this.usbA = usbA;
        this.usbC = usbC;
        this.desayuno = desayuno;
    }
    public int getId() {
        return this.id;}
    public boolean isBalcon() {
            return this.balcon;}
    public String getUbicacion() {
            return this.ubicacion;}
    public boolean isVista() {
        return this.vista;
    }
    public boolean isCocinaIntegrada() {
        return this.cocinaIntegrada;
    }
    public String getTipoHabitacion() {
        return this.tipoHabitacion;
    }
    public ArrayList<Cama> getCamas() {
        return this.camas;
    }
    public void addCama(Cama cama){
        this.camas.add(cama);
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public int getEspacio(){
        int espacio = 0;
        for(int i=0;i<camas.size();i++){
            espacio += camas.get(i).getCantidadPersonas();
        }
        return espacio;
    }
    public String textoInventario(){
        String retorno = ubicacion + " " + "Id->" + this.id + ": Habitación tipo " + this.tipoHabitacion + " con capacidad para " + getEspacio() + " personas.\n";
        retorno += "Cocina Integrada:" + cocinaIntegrada+"\nVista:" + vista + "\nBalcon:" + balcon + "\nCamas:\n";
        for(int i=1;i<=camas.size();i++){
            retorno += "    Cama " + i + ":" + camas.get(i-1).stringFactura() + "\n";
        }
        retorno += "Reservas:\n";
        for(int i=0;i<reservas.size();i++){
            if(reservas.get(i).isCancelado() == false){
            retorno += (i)+":"+reservas.get(i).stringInventario() + "\n";}
        }
        return retorno;
    }
    public float getTamañoMts() {
        return tamañoMts;
    }
    public boolean isAire() {
        return aire;
    }
    public boolean isCalefaccion() {
        return calefaccion;
    }
    public boolean isTv() {
        return tv;
    }
    public boolean isCafetera() {
        return cafetera;
    }
    public boolean isRopaCama() {
        return ropaCama;
    }
    public boolean isTapetes() {
        return tapetes;
    }
    public boolean isPlancha() {
        return plancha;
    }
    public boolean isSecador() {
        return secador;
    }
    public int getVoltaje() {
        return voltaje;
    }
    public boolean isUsbA() {
        return usbA;
    }
    public boolean isUsbC() {
        return usbC;
    }
    public boolean isDesayuno() {
        return desayuno;
    }

    
}
