package edu.uees.disenosoftware.tutorias.interfaces;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public interface INotificador {

    void enviarConfirmacion(Reserva reserva);

    void enviarCancelacion(Reserva reserva);
}