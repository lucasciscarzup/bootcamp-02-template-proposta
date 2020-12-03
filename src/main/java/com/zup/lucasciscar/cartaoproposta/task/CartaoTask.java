package com.zup.lucasciscar.cartaoproposta.task;

import com.zup.lucasciscar.cartaoproposta.client.CartaoClient;
import com.zup.lucasciscar.cartaoproposta.dto.response.CartaoClientResponse;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.model.Proposta;
import com.zup.lucasciscar.cartaoproposta.repository.CartaoRepository;
import com.zup.lucasciscar.cartaoproposta.repository.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Component
public class CartaoTask {

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelayString = "${periodicidade.cartao-task}")
    private void criarCartao() {
        List<Proposta> propostasElegiveis = propostaRepository.findByStatusAndNumeroCartaoIsNull(Proposta.Status.ELEGIVEL);
        if(!propostasElegiveis.isEmpty()) {
            for(Proposta proposta : propostasElegiveis) {
                try {
                    CartaoClientResponse cartaoClientResponse = cartaoClient.associarCartao(proposta.getId());
                    Cartao cartao = new Cartao(cartaoClientResponse.getId(), cartaoClientResponse.getLimite(), proposta);

                    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                    transactionTemplate.execute(status -> {
                        cartaoRepository.save(cartao);

                        proposta.setNumeroCartao(cartao.getNumero());
                        propostaRepository.save(proposta);

                        return true;
                    });
                } catch(FeignException ex) {
                    // Se voar Exception não faz nada e espera a próxima iteração
                }
            }
        }
    }
}
