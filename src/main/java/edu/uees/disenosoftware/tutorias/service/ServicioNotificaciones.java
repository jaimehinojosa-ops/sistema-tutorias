package edu.uees.disenosoftware.tutorias.service;

import edu.uees.disenosoftware.tutorias.interfaces.INotificador;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public class ServicioNotificaciones {

    private INotificador notificador;

    public ServicioNotificaciones(INotificador notificador) {
        this.notificador = notificador;
    }

    public void enviarConfirmacion(Reserva reserva) {
        notificador.enviarConfirmacion(reserva);
    }

    public void enviarCancelacion(Reserva reserva) {
        notificador.enviarCancelacion(reserva);
    }
}