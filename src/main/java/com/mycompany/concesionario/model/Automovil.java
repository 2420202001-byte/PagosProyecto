package com.mycompany.concesionario.model;

public abstract class Automovil {
    private final String matricula; // atributo único
    private String marca;
    private String modelo;

    protected Automovil(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    // Polimorfismo: cada derivada calcula distinto
    public abstract double calcularAutonomiaKm();

    public abstract String getTipo();
}
