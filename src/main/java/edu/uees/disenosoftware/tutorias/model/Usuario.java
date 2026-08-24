package edu.uees.disenosoftware.tutorias.model;

public abstract class Usuario {

    private String idUsuario;
    private String nombre;
    private String correo;
    private String password;
    private boolean estado;

    public void iniciarSesion() {
        System.out.println("Sesión iniciada");
    }

    public void actualizarPerfil() {
        System.out.println("Perfil actualizado");
    }
}
