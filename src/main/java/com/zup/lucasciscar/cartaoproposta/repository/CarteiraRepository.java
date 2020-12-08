package com.zup.lucasciscar.cartaoproposta.repository;

import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {

    Optional<Carteira> findByCartaoAndTipo(Cartao cartao, Carteira.Tipo tipo);
}
