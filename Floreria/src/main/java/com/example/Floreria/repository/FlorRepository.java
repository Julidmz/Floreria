package com.example.Floreria.repository;

import java.util.List;

import com.example.Floreria.model.Flor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlorRepository extends JpaRepository<Flor, Long> {

    public List<Flor> findByNombreFlor(String nombreFlor);

}
