package org.iesalandalus.programacion.reservashotel.dominio;

public class Habitacion {
    public double MIN_PRECIO_HABITACION;
    public double MAX_PRECIO_HABITACION;
    public int MIN_NUMERO_PUERTA;
    public int MAX_NUMERO_PUERTA;
    public int MIN_NUMERO_PLANTA;
    public int MAX_NUMERO_PLANTA;
    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;
    private String descripcion;
}
