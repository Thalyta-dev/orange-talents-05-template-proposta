package br.com.zup.propostas.ServicosExternos.AnaliseFinanceira;

import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDadosFinanceirosFallback implements ConsultaDadosSolicitante {

    @Override
    public PropostaConsultaDadosResponse consultaDadosSolicitante(PropostaConsultaDadosRequest request) {
         return new PropostaConsultaDadosResponse(request.getIdProposta(), request.getDocumento(), request.getNome(), PropostaRetorno.COM_RESTRICAO);
    }
}