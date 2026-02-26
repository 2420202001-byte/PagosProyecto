package com.mycompany.concesionario.model;

public class AutomovilElectrico extends Automovil implements IAplicable {
    private double bateriaKWh;
    private double consumoKWhPorKm; // mientras menor, más autonomía

    public AutomovilElectrico(String matricula, String marca, String modelo,
                              double bateriaKWh, double consumoKWhPorKm) {
        super(matricula, marca, modelo);
        this.bateriaKWh = bateriaKWh;
        this.consumoKWhPorKm = consumoKWhPorKm;
    }

    public double getBateriaKWh() { return bateriaKWh; }
    public double getConsumoKWhPorKm() { return consumoKWhPorKm; }

    @Override
    public double calcularAutonomiaKm() {
        return bateriaKWh / consumoKWhPorKm;
    }

    @Override
    public String getTipo() {
        return "Eléctrico";
    }

    @Override
    public void aplicarModoEco() {
        // mejora el consumo un 10% (ejemplo)
        this.consumoKWhPorKm = this.consumoKWhPorKm * 0.90;
    }
}
