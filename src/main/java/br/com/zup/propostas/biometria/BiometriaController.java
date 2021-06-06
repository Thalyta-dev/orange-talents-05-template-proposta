package br.com.zup.propostas.biometria;


import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
public class BiometriaController {

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("cartao/{id}/biometria")
    public ResponseEntity<?> adicionaBiometriaCartao(@RequestBody @Valid BiometriaRequest biometriaRequest, @PathVariable String id, UriComponentsBuilder uriBuilder){

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ErrosDto> errosDtos = biometriaRequest.verificaSeEstaBase64();

        if (!errosDtos.isEmpty()){
            return ResponseEntity.badRequest().body(errosDtos);
        }

        Cartao cartaoComBiometria = cartao.get().associaBiometriaAoCartão(biometriaRequest.toModel(cartao.get()));

        cartaoRepository.save(cartaoComBiometria);

        URI uri = uriBuilder.path("/cartao/{id}/biometria").buildAndExpand(cartao.get().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
