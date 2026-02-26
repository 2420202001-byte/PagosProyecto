package com.mycompany.concesionario.controller;

import com.mycompany.concesionario.model.*;
import com.mycompany.concesionario.service.AutomovilService;
import java.util.List;
import java.util.Optional;

public class AutomovilController {
    private final AutomovilService service;

    public AutomovilController(AutomovilService service) {
        this.service = service;
    }

    public void insertarGasolina(String mat, String marca, String modelo,
                                 double capacidadLitros, double consumoKmPorLitro) {
        Automovil a = new AutomovilGasolina(mat, marca, modelo,
                new TanqueCombustible(capacidadLitros), consumoKmPorLitro);
        service.insertar(a);
    }

    public void insertarElectrico(String mat, String marca, String modelo,
                                  double bateriaKWh, double consumoKWhPorKm) {
        Automovil a = new AutomovilElectrico(mat, marca, modelo, bateriaKWh, consumoKWhPorKm);
        service.insertar(a);
    }

    public Optional<Automovil> buscar(String mat) { return service.buscar(mat); }
    public boolean eliminar(String mat) { return service.eliminar(mat); }
    public List<Automovil> listar() { return service.listar(); }

    public double calcularAutonomia(String mat) {
        Automovil a = service.buscar(mat).orElseThrow(() -> new IllegalArgumentException("No existe"));
        return a.calcularAutonomiaKm(); // polimorfismo
    }

    public void aplicarModoEcoSiElectrico(String mat) {
        Automovil a = service.buscar(mat).orElseThrow(() -> new IllegalArgumentException("No existe"));
        if (a instanceof IAplicable aplicable) {
            aplicable.aplicarModoEco();
        } else {
            throw new IllegalArgumentException("No aplica modo eco a gasolina");
        }
    }
}
