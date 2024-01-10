package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar huesped"),
    BUSCAR_HUESPED("Buscar huesped"),
    BORRAR_HUESPED("Borrar huesped"),
    MOSTRAR_HUESPEDES("Mostrar huespedes"),
    INSERTAR_HABITACION("Insertar habitacion"),
    BUSCAR_HABITACION("Buscar habitacion"),
    BORRAR_HABITACION("Borrar habitacion"),
    MOSTRAR_HABITACIONES("Mostrar habitacion"),
    INSERTAR_RESERVA("Insertar reserva"),
    ANULAR_RESERVA("Anular reserva"),
    MOSTRAR_RESERVAS("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad");

    private String mensajeAMostrar;

    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar = mensajeAMostrar;
    }

    @Override
    public String toString() {
        return mensajeAMostrar;
    }
}
