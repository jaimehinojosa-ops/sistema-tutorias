package edu.uees.disenosoftware.tutorias.repository;

import java.util.HashMap;
import java.util.Map;

import edu.uees.disenosoftware.tutorias.interfaces.IRepositorioReservas;
import edu.uees.disenosoftware.tutorias.model.Reserva;

public class RepositorioReservas implements IRepositorioReservas {

    private final Map<String, Reserva> reservas = new HashMap<>();

    @Override
    public void guardar(Reserva reserva) {
        reservas.put(reserva.getIdReserva(), reserva);
    }

    @Override
    public Reserva buscar(String id) {
        return reservas.get(id);
    }

    @Override
    public void eliminar(String id) {
        reservas.remove(id);
    }
}