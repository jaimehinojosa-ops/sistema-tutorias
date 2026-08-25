package edu.uees.disenosoftware.tutorias.service;

import edu.uees.disenosoftware.tutorias.interfaces.INotificador;
import edu.uees.disenosoftware.tutorias.interfaces.IRepositorioReservas;
import edu.uees.disenosoftware.tutorias.model.Horario;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public class ServicioReservas {

    private IRepositorioReservas repositorio;
    private INotificador notificador;

    public ServicioReservas(
            IRepositorioReservas repositorio,
            INotificador notificador) {

        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public void crearReserva(
            Reserva reserva,
            Horario horario) {

        if (horario.validarDisponibilidad()) {

            reserva.confirmar();

            horario.marcarReservado();

            repositorio.guardar(reserva);

            notificador.enviarConfirmacion(reserva);
        }
    }

    public void cancelarReserva(Reserva reserva) {

        reserva.cancelar();

        notificador.enviarCancelacion(reserva);
    }

    public boolean validarDisponibilidad(Horario horario) {
        return horario.validarDisponibilidad();
    }
}