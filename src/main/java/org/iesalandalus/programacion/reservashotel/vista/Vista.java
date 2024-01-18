package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        do {
            Consola.mostrarMenu();
            ejecutarOpcion(Consola.elegirOpcion());
        } while (Consola.elegirOpcion() != Opcion.SALIR);
        terminar();
    }

    public void terminar() {
        System.out.println("Gracias por utilizar nuestra aplicación. ¡Hasta pronto!");
    }

    private void ejecutarOpcion(Opcion opcion) {

        switch (opcion) {
            case SALIR -> {
                System.out.println("Cerrando la aplicación...");
                System.exit(0);
                break;
            }
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReserva();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> {

                TipoHabitacion tH = Consola.leerTipoHabitacion();
                LocalDate fechaI = Consola.leerFecha("Fecha de inicio de reserva:");
                LocalDate fechaF = Consola.leerFecha("Fecha de fin de reserva:");

                consultarDisponibilidad(tH, fechaI, fechaF);
            }
            case REALIZAR_CHECKIN -> realizarCheckin();
            case REALIZAR_CHECKOUT -> realizarCheckout();
        }
    }

    // Huesped -----------------------------------------------------
    public void insertarHuesped() {

        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            if (controlador.buscar(nuevoHuesped) == null) {
                try {
                    controlador.insertar(nuevoHuesped);
                    System.out.println("Huésped insertado correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar el huésped: " + e.getMessage());
                }
            } else {
                System.out.println("El huésped ya está registrado en el sistema.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer el huésped: " + e.getMessage());
        }
    }

    private void buscarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedEncontrado = controlador.buscar(huespedFicticio);
            if (huespedEncontrado != null) {
                System.out.println("Huésped encontrado: " + huespedEncontrado);
            } else {
                System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar el huésped: " + e.getMessage());
        }
    }

    private void borrarHuesped() {
        try {
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Huesped huespedBorrado = controlador.buscar(huespedFicticio);
            if (huespedBorrado != null) {
                controlador.borrar(huespedBorrado);
                System.out.println("Huésped borrado: " + huespedBorrado);
            } else {
                System.out.println("No se encontró ningún huésped con el DNI proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar el huésped: " + e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarHuespedes() {
        if (controlador.getHuespedes().length == 0) {
            System.out.println("No hay huéspedes almacenados.");
        } else {
            System.out.println("Lista de huéspedes almacenados:");
            for (Huesped huesped : controlador.getHuespedes()) {
                System.out.println(huesped.toString());
            }
        }
    }

    // Habitacion -----------------------------------------------------
    private void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();

            if (controlador.buscar(nuevaHabitacion) == null) {
                try {
                    controlador.insertar(nuevaHabitacion);
                    System.out.println("Habitacion insertada correctamente.");
                } catch (OperationNotSupportedException e) {
                    System.out.println("Error al insertar la habitacion: " + e.getMessage());
                }
            } else {
                System.out.println("La habitacion ya está registrada en el sistema.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al leer la habitación: " + e.getMessage());
        }
    }

    private void buscarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionEncontrada = controlador.buscar(habitacionFicticia);
            if (habitacionEncontrada != null) {
                System.out.println("Habitación encontrada: " + habitacionEncontrada);
            } else {
                System.out.println("La habitación buscada no se encuentra en la colección.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar la habitación: " + e.getMessage());
        }
    }

    private void borrarHabitacion() {
        try {
            Habitacion habitacionFicticia = Consola.leerHabitacionPorIdentificador();
            Habitacion habitacionBorrada = controlador.buscar(habitacionFicticia);
            if (habitacionBorrada != null) {
                controlador.borrar(habitacionBorrada);
                System.out.println("Habitacion borrada: " + habitacionBorrada);
            } else {
                System.out.println("No se encontro ninguna habitacion con el identificador proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al buscar la habitación: " + e.getMessage());
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private void mostrarHabitaciones() {
        if (controlador.getHabitaciones().length == 0) {
            System.out.println("No hay habitaciones almacenadas.");
        } else {
            System.out.println("Lista de habitaciones almacenadas:");
            for (Habitacion habitacion : controlador.getHabitaciones()) {
                System.out.println(habitacion.toString());
            }
        }
    }

    // Reserva -----------------------------------------------------
    private void insertarReserva() {
        try {
            Reserva reservaFicticia = Consola.leerReserva();

            if (reservaFicticia == null) {
                System.out.println("No se pudo leer la reserva.");
                return;
            }

            Huesped huespedFicticio = reservaFicticia.getHuesped();
            Habitacion habitacionFicticia = reservaFicticia.getHabitacion();

            Huesped huespedReal = controlador.buscar(huespedFicticio);
            Habitacion habitacionReal = controlador.buscar(habitacionFicticia);

            if (huespedReal == null) {
                throw new NoSuchElementException("El huésped con el DNI proporcionado no se encuentra en el sistema.");
            }

            if (habitacionReal == null) {
                throw new NoSuchElementException("La habitación con el identificador proporcionado no se encuentra en el sistema.");
            }

            Reserva nuevaReserva = new Reserva(huespedReal, habitacionReal, reservaFicticia.getRegimen(),
                    reservaFicticia.getFechaInicioReserva(), reservaFicticia.getFechaFinReserva(), reservaFicticia.getNumeroPersonas());

            Habitacion habitacionDeseada = nuevaReserva.getHabitacion();
            Habitacion habitacionDisponible = consultarDisponibilidad(habitacionDeseada.getTipoHabitacion(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva());

            if (habitacionDisponible != null) {
                nuevaReserva.setHabitacion(habitacionDisponible);
                if (controlador.buscar(nuevaReserva) == null) {
                    try {
                        controlador.insertar(nuevaReserva);
                        System.out.println("Reserva insertada correctamente.");
                    } catch (OperationNotSupportedException e) {
                        System.out.println("Error al insertar la reserva: " + e.getMessage());
                    }
                } else {
                    System.out.println("La reserva ya está registrada en el sistema.");
                }
            } else {
                System.out.println("No hay disponibilidad para el tipo de habitación deseada en el periodo indicado.");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarReservas(Huesped huesped) {
        try {
            Reserva[] reservasDelHuesped = controlador.getReservas(huesped);
            if (reservasDelHuesped.length == 0) {
                System.out.println("No hay reservas para el huésped indicado.");
            } else {
                System.out.println("Lista de reservas del huésped:");
                for (Reserva reserva : reservasDelHuesped) {
                    System.out.println(reserva.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar las reservas: " + e.getMessage());
        }
    }

    private void listarReservas(TipoHabitacion tipoHabitacion) {
        try {
            Reserva[] reservasTipoHabitacion = controlador.getReservas(tipoHabitacion);
            if (reservasTipoHabitacion.length == 0) {
                System.out.println("No hay reservas para el tipo de habitación indicado.");
            } else {
                System.out.println("Lista de reservas del tipo de habitación:");
                for (Reserva reserva : reservasTipoHabitacion) {
                    System.out.println(reserva.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar las reservas: " + e.getMessage());
        }
    }

    private Reserva[] getReservasAnulables(Reserva[] reservasAAnular) {
        List<Reserva> reservasAnulables = new ArrayList<>();
        LocalDate hoy = LocalDate.now(); // Obtenemos la fecha actual.

        for (Reserva reserva : reservasAAnular) {
            if (reserva.getFechaInicioReserva().isAfter(hoy)) {
                // Si la fecha de inicio de la reserva aún no ha llegado, es anulable.
                reservasAnulables.add(reserva);
            }
        }
        // Convertimos la lista de reservas anulables a un array y lo devolvemos.
        return reservasAnulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        try {
            System.out.println("Anular su reserva:");
            Huesped huespedFicticio = Consola.getHuespedPorDni();
            Reserva[] reservasAnulables = getReservasAnulables(controlador.getReservas(huespedFicticio));

            if (reservasAnulables.length == 0) {
                throw new NoSuchElementException("El huésped no tiene reservas anulables.");
            } else if (reservasAnulables.length == 1) {
                System.out.println("El huésped tiene una reserva anulable: " + reservasAnulables[0]);
                System.out.println("¿Desea anular esta reserva? (S/N):");
                String respuesta = Entrada.cadena();
                if (respuesta.equalsIgnoreCase("S")) {
                    controlador.borrar(reservasAnulables[0]);
                    System.out.println("La reserva ha sido anulada.");
                } else {
                    System.out.println("Anulación cancelada.");
                }
            } else {
                System.out.println("El huésped tiene varias reservas anulables:");
                for (int i = 0; i < reservasAnulables.length; i++) {
                    System.out.println((i + 1) + ".- " + reservasAnulables[i]);
                }
                System.out.println("Introduce el número de la reserva que deseas anular:");
                int indice = Entrada.entero() - 1;
                if (indice >= 0 && indice < reservasAnulables.length) {
                    controlador.borrar(reservasAnulables[indice]);
                    System.out.println("La reserva seleccionada ha sido anulada.");
                } else {
                    System.out.println("Número de reserva no válido.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al anular la reserva: " + e.getMessage());
        }
    }

    public void mostrarReservas() {
        if (controlador.getReservas().length == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            System.out.println("Lista de todas las reservas almacenadas:");
            for (Reserva reserva : controlador.getReservas()) {
                System.out.println(reserva.toString());
            }
        }
    }

    private Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : controlador.getHabitaciones()) {
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                boolean estaDisponible = true;
                for (Reserva reserva : controlador.getReservas()) {
                    if (reserva.getHabitacion().equals(habitacion) &&
                            !reserva.getFechaFinReserva().isBefore(fechaInicio) &&
                            !reserva.getFechaInicioReserva().isAfter(fechaFin)) {
                        estaDisponible = false;
                        break;
                    }
                }
                if (estaDisponible) {
                    return habitacion;
                }
            }
        }
        System.out.println("Esa habitacion no está disponible.");
        return null;
    }

    private int getNumElementosNoNulos(Reserva[] reservas) {
        return 1;
    }

    private void realizarCheckin() throws NoSuchElementException{
        System.out.println("introduce el huesped que ha realizado la reserva");
        Huesped huesped = controlador.buscar(Consola.getHuespedPorDni());
        Reserva[] reservasHuesped = controlador.getReservas(huesped);
        if (reservasHuesped.length > 1){
            System.out.println("Elija a que reserva quiere hacer el checkin introduciendo su numero:");
            for (int i = 0; i < reservasHuesped.length; i++){
                System.out.println(i + ".- " + reservasHuesped.toString());
            }
            int eleccion = Entrada.entero();
            if (eleccion >= 0 && eleccion < reservasHuesped.length) {
                controlador.realizarCheckin(reservasHuesped[eleccion], LocalDateTime.now());
            } else {
                throw new NoSuchElementException("Número de reserva no válido.");
            }
        } else if (reservasHuesped.length == 1) {
            controlador.realizarCheckin(reservasHuesped[0], LocalDateTime.now());
        } else {
            throw new NoSuchElementException("No se encontraron reservas para el huésped proporcionado.");
        }
    }

    private void realizarCheckout() throws NoSuchElementException {
        System.out.println("Introduce el DNI del huésped que ha realizado la reserva");
        Huesped huesped = controlador.buscar(Consola.getHuespedPorDni());
        Reserva[] reservasHuesped = controlador.getReservas(huesped);
        if (reservasHuesped.length > 1){
            System.out.println("Elija a qué reserva quiere hacer el checkout introduciendo su número:");
            for (int i = 0; i < reservasHuesped.length; i++){
                System.out.println(i + ".- " + reservasHuesped[i].toString());
            }
            int eleccion = Entrada.entero();
            if (eleccion >= 0 && eleccion < reservasHuesped.length) {
                controlador.realizarCheckout(reservasHuesped[eleccion], LocalDateTime.now());
            } else {
                throw new NoSuchElementException("Número de reserva no válido.");
            }
        } else if (reservasHuesped.length == 1) {
            controlador.realizarCheckout(reservasHuesped[0], LocalDateTime.now());
        } else {
            throw new NoSuchElementException("No se encontraron reservas para el huésped proporcionado.");
        }
    }


}