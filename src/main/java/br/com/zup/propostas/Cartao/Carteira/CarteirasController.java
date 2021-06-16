package br.com.zup.propostas.Cartao.Carteira;


import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Cartao.StatusCartao;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaCartao;
import br.com.zup.propostas.ServicosExternos.Cartao.StatusCarteiraResponse;
import br.com.zup.propostas.TratandoErros.ErrosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CarteirasController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    SistemaCartao sistemaCartao;

    @PostMapping("cartao/{idCartao}/carteiras")
    public ResponseEntity adionaCarteirAoCartao(@RequestBody @Valid CarteirasRequest carteirasRequest,
                                                @PathVariable String idCartao, UriComponentsBuilder uriComponentsBuilder) {

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (cartao.get().getCartaoBloqueado().equals(StatusCartao.BLOQUEADO)) {
            return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão  está bloqueado"));
        }

        if (!cartao.get().verificaSeACarteiraJaEstaAssociada(carteirasRequest.getCarteira())){

            return ResponseEntity.unprocessableEntity().body(new ErrosDto("Carteira ", "A carteira " + carteirasRequest.getCarteira() + " já foi associada"));

        }

        StatusCarteiraResponse statusCarteira = sistemaCartao.adicionaCarteira(carteirasRequest, idCartao);

        if (statusCarteira.getResultado().equals(StatusCarteira.FALHA)){
            return ResponseEntity.badRequest().body(new ErrosDto("Carteira ", "Falhou em associar carteira"));

        }

        Carteiras carteiras = carteirasRequest.toModel(cartao.get(), statusCarteira.getId());

        cartao.get().associaCarteiraAoCartao(carteiras);
        cartaoRepository.save(cartao.get());

        URI uri = uriComponentsBuilder.path("cartao/{idCartao}/carteiras/{idCarteira}").buildAndExpand(cartao.get().getId(),carteiras.getId()).toUri();
        return ResponseEntity.created(uri).build();



    }
}
