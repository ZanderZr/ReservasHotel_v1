package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Modelo {
    public static final int CAPACIDAD = 45;
    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    public Modelo() {
    }

    public void comenzar() {
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }

    public void terminar() {
        System.out.println("Info: El modelo ha terminado.");
    }

    // Metodos para la gestion de Huesped:
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huespedes.buscar(huesped) == null) {
            huespedes.insertar(huesped);
        } else {
            throw new OperationNotSupportedException("El hu�sped ya est� registrado en el sistema.");
        }
    }

    public Huesped buscar(Huesped huesped) throws NoSuchElementException {
        Huesped huespedEncontrado = huespedes.buscar(huesped);
        if (huespedEncontrado == null) {
            throw new NoSuchElementException("El huesped buscado no existe.");
        }
        return huespedes.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huespedes.buscar(huesped) == null) {
            throw new OperationNotSupportedException("El hu�sped a borrar no existe.");
        }
        huespedes.borrar(huesped);
    }

    public Huesped[] getHuespedes() {
        try {
            Huesped[] original = huespedes.get();
            Huesped[] copia = new Huesped[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Huesped(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener los hu�spedes: " + e.getMessage());
            return null;
        }
    }

    // Metodos para la gestion de Habitacion:
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            habitaciones.insertar(habitacion);
        } else {
            throw new OperationNotSupportedException("La habitacion ya est� registrada en el sistema.");
        }
    }

    public Habitacion buscar(Habitacion habitacion) throws NoSuchElementException {
        Habitacion habitacionEncontrada = habitaciones.buscar(habitacion);
        if (habitacionEncontrada == null) {
            throw new NoSuchElementException("La habitaci�n buscada no existe.");
        }
        return habitacionEncontrada;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitaciones.buscar(habitacion) == null) {
            throw new OperationNotSupportedException("La habitaci�n a borrar no existe.");
        }
        habitaciones.borrar(habitacion);
    }

    public Habitacion[] getHabitaciones() {
        try {
            Habitacion[] original = habitaciones.get();
            Habitacion[] copia = new Habitacion[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Habitacion(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
            return null;
        }
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion) {
        try {
            Habitacion[] original = habitaciones.get();
            Habitacion[] copia = new Habitacion[original.length];
            int indice = 0;
            for (Habitacion habitacion : original) {
                if (habitacion.getTipoHabitacion() == tipoHabitacion) {
                    copia[indice++] = new Habitacion(habitacion);  // Usar el constructor de copia
                }
            }
            return copia;  // Devolver array del tama�o correcto
        } catch (Exception e) {
            System.out.println("Error al obtener las habitaciones: " + e.getMessage());
            return null;
        }
    }

    // Metodos para la gestion de Reserva:
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reservas.buscar(reserva) == null) {
            reservas.insertar(reserva);
        } else {
            throw new OperationNotSupportedException("La reserva ya est� registrada en el sistema.");
        }
    }
    public Reserva buscar(Reserva reserva) throws NoSuchElementException {
        Reserva reservaEncontrada = reservas.buscar(reserva);
        if (reservaEncontrada == null) {
            throw new NoSuchElementException("La reserva buscada no existe.");
        }
        return reservaEncontrada;
    }
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reservas.buscar(reserva) == null) {
            throw new OperationNotSupportedException("La reserva a borrar no existe.");
        }
        reservas.borrar(reserva);
    }
    public Reserva[] getReservas() {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            for (int i = 0; i < original.length; i++) {
                copia[i] = new Reserva(original[i]);
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }

    public Reserva[] getReservas(Huesped huesped) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHuesped() == huesped) {
                    copia[indice++] = new Reserva(reserva);
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion) {
                    copia[indice++] = new Reserva(reserva);
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        try {
            Reserva[] original = reservas.get();
            Reserva[] copia = new Reserva[original.length];
            int indice = 0;
            for (Reserva reserva : original) {
                if (reserva.getHabitacion() == habitacion) {
                    copia[indice++] = new Reserva(reserva);
                }
            }
            return copia;
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
            return null;
        }
    }

   public void realizarCheckin (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckin(reserva, fecha);
   }
   public void realizarCheckout (Reserva reserva, LocalDateTime fecha){
        reservas.realizarCheckout(reserva, fecha);
   }
}
