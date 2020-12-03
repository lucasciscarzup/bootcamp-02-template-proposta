package com.zup.lucasciscar.cartaoproposta.repository;

import com.zup.lucasciscar.cartaoproposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

    Optional<Proposta> findByDocumento(String documento);

    List<Proposta> findByStatusAndNumeroCartaoIsNull(Proposta.Status status);
}
