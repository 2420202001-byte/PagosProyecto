package com.mycompany.concesionario.model;

public class TanqueCombustible {
    private double capacidadLitros;

    public TanqueCombustible(double capacidadLitros) {
        this.capacidadLitros = capacidadLitros;
    }

    public double getCapacidadLitros() { return capacidadLitros; }
    public void setCapacidadLitros(double capacidadLitros) { this.capacidadLitros = capacidadLitros; }
}
