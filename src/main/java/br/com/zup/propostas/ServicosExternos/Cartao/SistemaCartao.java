package br.com.zup.propostas.ServicosExternos.Cartao;


import br.com.zup.propostas.Cartao.AvisosViagem.AvisosViagemRequest;
import br.com.zup.propostas.Cartao.CartaoRequest;
import br.com.zup.propostas.Cartao.Carteira.CarteirasRequest;
import br.com.zup.propostas.Proposta.PropostaConsultaDadosRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaCartaoFallBack;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaResponsavelRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.StatusBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name= "gerarCartao", url = "http://localhost:8888/api/cartoes/", fallback = SistemaCartaoFallBack.class)
public interface SistemaCartao {

    @GetMapping
    CartaoRequest gerarCartao(@RequestParam Long idProposta);

    @PostMapping("{idCartao}/bloqueios")
    StatusBloqueioResponse bloquearCartao(@RequestBody SistemaResponsavelRequest sistemaResponsavel, @PathVariable String idCartao);

    @PostMapping("{idCartao}/avisos")
    StatusAvisosResponses adicionarAvisos(@RequestBody AvisosViagemRequest avisosViagemRequest, @PathVariable String idCartao);

    @PostMapping("{idCartao}/carteiras")
    StatusCarteiraResponse adicionaCarteira(@RequestBody CarteirasRequest carteirasRequest, @PathVariable String idCartao);
}
