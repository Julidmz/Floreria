package com.example.Floreria.dao;

import com.example.Floreria.model.Flor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Floreria.repository.FlorRepository;

import java.util.List;

@Component
public class FlorDAO {

    @Autowired
    private FlorRepository florRepository;

    // Obtener todas las flores
    public List<Flor> obtenerFlores() {
        return florRepository.findAll();
    }

    // Insertar una nueva flor
    public boolean insertarFlor(Flor flor) {
        try {
            florRepository.save(flor);  // Usamos save, que inserta o actualiza
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Eliminar una flor por ID
    public boolean eliminarFlor(Long id) {  // Cambiado a Long
        try {
            florRepository.deleteById(id);  // Eliminar por ID
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener una flor por ID
    public Flor obtenerFlorPorId(Long id) {  // Cambiado a Long
        return florRepository.findById(id).orElse(null);  // Retorna null si no encuentra la flor
    }

    // Actualizar una flor
    public boolean actualizarFlor(Flor flor) {
        try {
            florRepository.save(flor);  // Usamos save para actualizar
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}



