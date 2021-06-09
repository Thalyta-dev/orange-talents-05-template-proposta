package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaCartao;
import net.bytebuddy.implementation.bytecode.Throw;
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
    SistemaCartao sistemaCartao;


    @Scheduled(cron = "5/10 * * * * * ")
    public void associaCartaoaPropostasElegiveisQueFalharam() {

        List<Proposta> propostasElegiveisSemCartao =
                propostaRepository.findByStatusPropostaElegivelSemCartao();

        if (!propostasElegiveisSemCartao.isEmpty()){
            propostasElegiveisSemCartao.stream().map(e ->
            {
                try {
                    return associaCartao(e);

                } catch (Exception exception) {

                    exception.printStackTrace();

                }
                return null;
            })
                    .collect(Collectors.toList());

        }

    }

    public Cartao associaCartao(Proposta proposta) throws Exception {

        CartaoRequest cartao = sistemaCartao.gerarCartao(proposta.getId().toString());

        if (cartao == null){

            throw new Exception("O Sistema de associar o cartão está indisponivel");

        }
        Cartao save = cartaoRepository.save(cartao.toModel(proposta));
        return save;

    }

}
