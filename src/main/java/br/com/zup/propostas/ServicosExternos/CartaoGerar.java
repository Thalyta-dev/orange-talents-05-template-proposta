package br.com.zup.propostas.ServicosExternos;


import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name= "gerarCartao", url = "http://localhost:8888")
public interface CartaoGerar {

    @PostMapping("/api/cartoes")
    ResponseEntity<CartaoRequest> gerarCartao(@RequestBody PropostaConsultaDadosRequest request);
}
