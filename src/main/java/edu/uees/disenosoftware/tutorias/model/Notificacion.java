package edu.uees.disenosoftware.tutorias.model;
import java.time.LocalDate;

public class Notificacion {

    private String idNotificacion;
    private String mensaje;
    private LocalDate fechaEnvio;
    private String tipo;

    public String generarMensaje() {
        return mensaje;
    }
}