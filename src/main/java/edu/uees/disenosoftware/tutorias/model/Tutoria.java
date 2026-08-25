package edu.uees.disenosoftware.tutorias.model;
import java.time.LocalDate;

public class Tutoria {

    private LocalDate fecha;
    private String tema;
    private String observaciones;
    private String estado;

    private Reserva reserva;

    public void iniciar() {
        estado = "EN_CURSO";
    }

    public void finalizar() {
        estado = "FINALIZADA";
    }

    public void registrarObservaciones(String texto) {
        observaciones = texto;
    }
}