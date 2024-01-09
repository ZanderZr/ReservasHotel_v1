package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.Month;

public class MainApp {
    
    public static void main(String[] args) {

        Huesped h = new Huesped("BeNITO camelas pelotas", "666555444", "jc@gmail.com", "76660251D", LocalDate.of(1994, 01,31));

        String nom = h.toString();
        System.out.println(nom);
        String i = h.getIniciales();
        System.out.println(i);
    }
}


