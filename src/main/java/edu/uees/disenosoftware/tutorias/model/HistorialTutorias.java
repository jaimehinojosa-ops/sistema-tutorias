package edu.uees.disenosoftware.tutorias.model;
import java.util.ArrayList;
import java.util.List;

public class HistorialTutorias {

    private List<Tutoria> tutorias = new ArrayList<>();

    public void registrarTutoria(Tutoria tutoria) {
        tutorias.add(tutoria);
    }

    public List<Tutoria> consultarHistorial() {
        return tutorias;
    }
}