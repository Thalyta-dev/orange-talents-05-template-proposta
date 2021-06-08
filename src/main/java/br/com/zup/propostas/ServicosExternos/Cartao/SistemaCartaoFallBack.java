package br.com.zup.propostas.ServicosExternos.Cartao;

import br.com.zup.propostas.Cartao.AvisosViagem.AvisosViagemRequest;
import br.com.zup.propostas.Cartao.AvisosViagem.StatusAvisos;
import br.com.zup.propostas.Cartao.Bloqueio.StatusBloqueio;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SistemaCartaoFallBack implements SistemaCartao {

    @Override
    public ResponseEntity<CartaoRequest> gerarCartao(PropostaConsultaDadosRequest request) {

        return null;
    }

    @Override
    public StatusBloqueioResponse bloquearCartao(SistemaResponsavelRequest sistemaResponsavel, String id) {
        return new StatusBloqueioResponse(StatusBloqueio.FALHA);
    }

    @Override
    public StatusAvisosResponses adicionarAvisos(AvisosViagemRequest avisosViagemRequest, String id) {
        return new StatusAvisosResponses(StatusAvisos.FALHA);
    }

}
