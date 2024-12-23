package com.example.Floreria.service;

import com.example.Floreria.dao.FlorDAO;
import com.example.Floreria.model.Flor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Agregar la importación de List
import java.util.List;

@Service
public class FlorService {

    @Autowired
    private FlorDAO florDAO;

    // Obtener todas las flores
    public List<Flor> obtenerTodasLasFlores() {
        return florDAO.obtenerFlores();
    }

    // Insertar una nueva flor
    public boolean insertarFlor(Flor flor) {
        return florDAO.insertarFlor(flor);
    }

    // Eliminar una flor por ID
    public boolean eliminarFlor(int id) {
        try {
            // Convertir el id de int a Long antes de pasarlo a FlorDAO
            return florDAO.eliminarFlor((long) id);  // Cambiado a Long
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener una flor por ID
    public Flor obtenerFlorPorId(int id) {
        // Convertir el id de int a Long antes de pasarlo a FlorDAO
        return florDAO.obtenerFlorPorId((long) id);  // Cambiado a Long
    }

    // Actualizar una flor
    public boolean actualizarFlor(Flor flor) {
        return florDAO.actualizarFlor(flor);
    }
}



