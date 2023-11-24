package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {
    List<Sabor> findByTipo(String tipo);
}
