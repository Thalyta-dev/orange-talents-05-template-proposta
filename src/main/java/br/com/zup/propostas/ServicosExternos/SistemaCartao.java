package br.com.zup.propostas.ServicosExternos;


import br.com.zup.propostas.Cartao.Bloqueio.StatusBloqueio;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name= "gerarCartao", url = "http://localhost:8888")
public interface ServicoCartao {

    @PostMapping("/api/cartoes")
    ResponseEntity<CartaoRequest> gerarCartao(@RequestBody PropostaConsultaDadosRequest request);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    StatusBloqueio bloquearCartao(@RequestBody SistemaResponsavel sistemaResponsavel, @PathVariable String id);
}
