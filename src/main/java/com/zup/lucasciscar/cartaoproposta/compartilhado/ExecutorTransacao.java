package com.zup.lucasciscar.cartaoproposta.compartilhado;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class ExecutorTransacao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <T> T salvar(T objeto) {
        entityManager.persist(objeto);
        return objeto;
    }

    @Transactional
    public <T> T atualizar(T objeto) {
        return entityManager.merge(objeto);
    }
}
