package com.mycompany.concesionario.repo;

import com.mycompany.concesionario.model.Automovil;
import java.util.List;
import java.util.Optional;

public interface AutomovilRepository {
    void save(Automovil a); // create/update
    Optional<Automovil> findByMatricula(String matricula);
    boolean deleteByMatricula(String matricula);
    List<Automovil> findAll();
    boolean existsByMatricula(String matricula);
}
