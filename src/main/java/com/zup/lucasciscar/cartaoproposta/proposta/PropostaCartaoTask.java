package com.zup.lucasciscar.cartaoproposta.proposta;

import com.zup.lucasciscar.cartaoproposta.compartilhado.ExecutorTransacao;
import com.zup.lucasciscar.cartaoproposta.compartilhado.client.CartaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PropostaCartaoTask {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoClient cartaoClient;
    @Autowired
    private ExecutorTransacao executorTransacao;

    @Scheduled(fixedDelayString = "${periodicidade.proposta-cartao}")
    private void associarCartao() {
        List<Proposta> propostasElegiveis = propostaRepository.findByStatusAndIdCartaoIsNull(Proposta.Status.ELEGIVEL);
        if(!propostasElegiveis.isEmpty()) {
            for(Proposta proposta : propostasElegiveis) {
                Map<String, Object> resultadoCartao = cartaoClient.associarCartao(proposta.getId());
                proposta.setIdCartao(resultadoCartao.get("id").toString());

                executorTransacao.atualizar(proposta);
            }
        }
    }
}
