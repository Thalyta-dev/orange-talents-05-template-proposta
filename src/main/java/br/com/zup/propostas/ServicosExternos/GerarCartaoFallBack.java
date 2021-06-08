package br.com.zup.propostas.ServicosExternos;

import br.com.zup.propostas.Cartao.Bloqueio.ResultadoBloqueio;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GerarCartaoFallBack implements ServicoCartao {

    @Override
    public ResponseEntity<CartaoRequest> gerarCartao(PropostaConsultaDadosRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ResultadoBloqueio> bloquearCartao(SistemaResponsavel sistemaResponsavel, String id) {
        return null;
    }

}
