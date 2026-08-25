package edu.uees.disenosoftware.tutorias.model;

import java.time.LocalDate;

public class Reserva {

    private String idReserva;
    private LocalDate fechaCreacion;
    private String estado;

    private Horario horario;
    private Estudiante estudiante;
    private Tutor tutor;

    public void confirmar() {
        estado = "CONFIRMADA";
    }

    public void cancelar() {
        estado = "CANCELADA";
    }

    public String obtenerEstado() {
        return estado;
    }

    public String getIdReserva() {
        return idReserva;
    }
}