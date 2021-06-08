package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.ServicosExternos.ServicoCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Component
public class AssociaCartaoProposta {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ServicoCartao cartaoGerar;


    @Scheduled(cron = "5/10 * * * * * ")
    public void associaCartaoaPropostasElegiveisQueFalharam() {

        List<Proposta> propostasElegiveisSemCartao =
                propostaRepository.findByStatusPropostaElegivelSemCartao();

        if (!propostasElegiveisSemCartao.isEmpty()){
            propostasElegiveisSemCartao.stream().map(e ->
                    associaCartao(e))
                    .collect(Collectors.toList());

        }

    }

    public Cartao associaCartao(Proposta proposta) {

        ResponseEntity<CartaoRequest> cartao =
                cartaoGerar.gerarCartao((new PropostaConsultaDadosRequest(proposta)));
        Cartao save = cartaoRepository.save(cartao.getBody().toModel(proposta));
        return save;

    }
}
