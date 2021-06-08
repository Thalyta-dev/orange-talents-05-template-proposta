package br.com.zup.propostas.ServicosExternos.Cartao;


import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaCartaoFallBack;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaResponsavelRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.StatusBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name= "gerarCartao", url = "http://localhost:8888", fallback = SistemaCartaoFallBack.class)
public interface SistemaCartao {

    @PostMapping("/api/cartoes")
    ResponseEntity<CartaoRequest> gerarCartao(@RequestBody PropostaConsultaDadosRequest request);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    StatusBloqueioResponse bloquearCartao(@RequestBody SistemaResponsavelRequest sistemaResponsavel, @PathVariable String id);
}
