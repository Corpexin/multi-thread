package com.corpex.prm005httpurlconnectionpost;

public class Alumno {
    private boolean repetidor;
    private int edad;
    private String direccion;
    private String telefono;
    private String curso;
    private String nombre;
    private String foto;


    public Alumno(boolean repetidor, int edad, String direccion, String telefono, String curso, String nombre, String foto) {
        this.foto = foto;
        this.nombre = nombre;
        this.curso = curso;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;
        this.repetidor = repetidor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }


}
