package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public Habitaciones(int capacidad){
        this.capacidad = capacidad;
        this.coleccionHabitaciones = new Habitacion[capacidad];
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    private Habitacion[] copiaProfundaHabitaciones() {
        Habitacion[] copiaProfunda = new Habitacion[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaProfunda[i] = new Habitacion(coleccionHabitaciones[i]);
        }
        return copiaProfunda;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitaci�n nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("La habitaci�n ya existe y no se admiten repetidos.");
        }
        if (tamanoSuperado()) {
            throw new OperationNotSupportedException("No se pueden insertar m�s habitaciones, se ha alcanzado la capacidad m�xima.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("Se ha superado la capacidad m�xima, esto no deber�a ocurrir.");
        }
        coleccionHabitaciones[tamano++] = habitacion;
    }

    public Habitacion buscar(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return coleccionHabitaciones[i];
            }
        }
        return null;
    }

    public void borrar(Habitacion habitacion) {
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private int buscarIndice(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion)) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
    }
}
