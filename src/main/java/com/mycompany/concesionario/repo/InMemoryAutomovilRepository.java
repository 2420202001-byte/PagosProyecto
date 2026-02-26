package com.mycompany.concesionario.repo;

import com.mycompany.concesionario.model.Automovil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAutomovilRepository implements AutomovilRepository {
    private final List<Automovil> data = new ArrayList<>();

    @Override
    public void save(Automovil a) {
        // si existe, reemplaza; si no, inserta
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getMatricula().equalsIgnoreCase(a.getMatricula())) {
                data.set(i, a);
                return;
            }
        }
        data.add(a);
    }

    @Override
    public Optional<Automovil> findByMatricula(String matricula) {
        return data.stream()
                .filter(x -> x.getMatricula().equalsIgnoreCase(matricula))
                .findFirst();
    }

    @Override
    public boolean deleteByMatricula(String matricula) {
        return data.removeIf(x -> x.getMatricula().equalsIgnoreCase(matricula));
    }

    @Override
    public List<Automovil> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public boolean existsByMatricula(String matricula) {
        return findByMatricula(matricula).isPresent();
    }
}
