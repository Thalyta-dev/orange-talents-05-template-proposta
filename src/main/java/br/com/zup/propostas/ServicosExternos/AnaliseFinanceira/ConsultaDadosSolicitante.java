package br.com.zup.propostas.ServicosExternos.AnaliseFinanceira;


import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name= "consultaDados", url = "${analise.proposta}", fallback = ConsultaDadosFinanceirosFallback.class)
public interface ConsultaDadosSolicitante{

    @PostMapping("${analise.proposta.recurso}")
    PropostaConsultaDadosResponse consultaDadosSolicitante(@RequestBody PropostaConsultaDadosRequest propostaConsultaDadosRequest);
}
