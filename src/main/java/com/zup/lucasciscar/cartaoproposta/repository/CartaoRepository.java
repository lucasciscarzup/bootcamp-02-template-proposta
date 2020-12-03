package com.zup.lucasciscar.cartaoproposta.repository;

import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
