package edu.uees.disenosoftware.tutorias.interfaces;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public interface IRepositorioReservas {

    void guardar(Reserva reserva);

    Reserva buscar(String id);

    void eliminar(String id);
}