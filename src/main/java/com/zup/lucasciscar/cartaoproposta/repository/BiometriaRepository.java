package com.zup.lucasciscar.cartaoproposta.repository;

import com.zup.lucasciscar.cartaoproposta.model.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, UUID> {
}
