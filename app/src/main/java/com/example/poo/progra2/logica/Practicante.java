package com.example.poo.progra2.logica;

public class Practicante {

    private String id;
    private String contrasena;

    public Practicante(String pId, String pContrasena) {
        setId(pId);
        setContrasena(pContrasena);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
