package br.com.zup.propostas.ServicosExternos.AnaliseFinanceira;


import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name= "consultaDados", url = "http://localhost:9999", fallback = ConsultaDadosFinanceirosFallback.class)
public interface ConsultaDadosSolicitante{

    @PostMapping("/api/solicitacao")
    PropostaConsultaDadosResponse consultaDadosSolicitante(@RequestBody PropostaConsultaDadosRequest propostaConsultaDadosRequest);
}
