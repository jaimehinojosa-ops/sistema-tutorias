package edu.uees.disenosoftware.tutorias.notification;

import edu.uees.disenosoftware.tutorias.interfaces.INotificador;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public class NotificadorEmail implements INotificador {

    @Override
    public void enviarConfirmacion(Reserva reserva) {
        System.out.println("Correo confirmación enviado");
    }

    @Override
    public void enviarCancelacion(Reserva reserva) {
        System.out.println("Correo cancelación enviado");
    }
}