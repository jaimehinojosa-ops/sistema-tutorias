package edu.uees.disenosoftware.tutorias.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean disponible;

    public boolean validarDisponibilidad() {
        return disponible;
    }

    public void marcarReservado() {
        disponible = false;
    }

    public void marcarDisponible() {
        disponible = true;
    }
}