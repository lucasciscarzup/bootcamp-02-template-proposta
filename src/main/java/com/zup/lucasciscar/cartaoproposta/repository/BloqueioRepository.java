package com.zup.lucasciscar.cartaoproposta.repository;

import com.zup.lucasciscar.cartaoproposta.model.Bloqueio;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, UUID> {

    Optional<Bloqueio> findByCartao(Cartao cartao);
}
