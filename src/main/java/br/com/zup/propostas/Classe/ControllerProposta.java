package br.com.zup.propostas.Classe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class ControllerProposta {

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<PropostaResponse> criaPrposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder){
        Proposta propostaSalva = propostaRepository.save(propostaRequest.toModel());

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(propostaSalva));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> criaPrposta(@PathVariable Long id){

        Optional<Proposta> propostaPorId = propostaRepository.findById(id);
        return ResponseEntity.ok().body(new PropostaResponse(propostaPorId.get()));

    }
}
