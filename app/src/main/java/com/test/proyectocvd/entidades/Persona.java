package com.test.proyectocvd.entidades;

public class Persona {
    private int id;
    private String dni,nombres;

    public Persona(int id, String dni, String nombres) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
    }

    public Persona(String dni, String nombres) {
        this.dni = dni;
        this.nombres = nombres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}







