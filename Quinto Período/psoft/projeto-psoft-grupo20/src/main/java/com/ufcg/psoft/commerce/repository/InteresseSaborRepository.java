package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.InteresseSabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresseSaborRepository extends JpaRepository<InteresseSabor, Long>{
}
