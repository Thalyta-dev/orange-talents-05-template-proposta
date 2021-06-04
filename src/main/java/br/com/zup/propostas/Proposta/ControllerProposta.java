package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.ServicosExternos.ConsultaDadosSolicitante;
import br.com.zup.propostas.ServicosExternos.PropostaConsultaDadosResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class ControllerProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaDadosSolicitante consultaDadosSolicitante;

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder){

        Proposta propostaSalva = propostaRepository.save(propostaRequest.toModel());

        try {

            PropostaConsultaDadosResponse propostaConsultaDadosResponse = consultaDadosSolicitante.consultaDadosSolicitante(new PropostaConsultaDadosRequest(propostaSalva));
            propostaSalva.setStatusProposta(propostaConsultaDadosResponse.retornStatusProposta());

        }catch (FeignException e){

            propostaSalva.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
            return ResponseEntity.status(e.status()).body(new PropostaResponse(propostaSalva));

        }

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(propostaSalva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> criaPrposta(@PathVariable Long id){

        Optional<Proposta> propostaPorId = propostaRepository.findById(id);
        return ResponseEntity.ok().body(new PropostaResponse(propostaPorId.get()));

    }


}
