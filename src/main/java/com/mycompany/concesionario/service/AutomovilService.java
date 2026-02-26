package com.mycompany.concesionario.service;

import com.mycompany.concesionario.model.Automovil;
import com.mycompany.concesionario.repo.AutomovilRepository;
import java.util.List;
import java.util.Optional;

public class AutomovilService {
    private final AutomovilRepository repo;

    public AutomovilService(AutomovilRepository repo) {
        this.repo = repo;
    }

    public void insertar(Automovil a) {
        if (a == null) throw new IllegalArgumentException("Automóvil null");
        if (repo.existsByMatricula(a.getMatricula())) {
            throw new IllegalArgumentException("Ya existe esa matrícula");
        }
        repo.save(a);
    }

    public Optional<Automovil> buscar(String matricula) {
        return repo.findByMatricula(matricula);
    }

    public boolean eliminar(String matricula) {
        return repo.deleteByMatricula(matricula);
    }

    public List<Automovil> listar() {
        return repo.findAll();
    }
}
