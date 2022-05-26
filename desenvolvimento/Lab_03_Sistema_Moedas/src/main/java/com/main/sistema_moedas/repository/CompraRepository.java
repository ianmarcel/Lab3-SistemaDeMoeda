package com.main.sistema_moedas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.sistema_moedas.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
