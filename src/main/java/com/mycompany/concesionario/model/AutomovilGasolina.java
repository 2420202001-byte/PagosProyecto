package com.mycompany.concesionario.model;

public class AutomovilGasolina extends Automovil {
    private TanqueCombustible tanque;     // Asociación/Composición
    private double consumoKmPorLitro;     // dato extra para el cálculo

    public AutomovilGasolina(String matricula, String marca, String modelo,
                             TanqueCombustible tanque, double consumoKmPorLitro) {
        super(matricula, marca, modelo);
        this.tanque = tanque;
        this.consumoKmPorLitro = consumoKmPorLitro;
    }

    public TanqueCombustible getTanque() { return tanque; }
    public double getConsumoKmPorLitro() { return consumoKmPorLitro; }

    @Override
    public double calcularAutonomiaKm() {
        return tanque.getCapacidadLitros() * consumoKmPorLitro;
    }

    @Override
    public String getTipo() {
        return "Gasolina";
    }
}
