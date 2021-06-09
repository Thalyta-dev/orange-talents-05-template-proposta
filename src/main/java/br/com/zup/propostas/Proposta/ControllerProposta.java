package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.ServicosExternos.AnaliseFinanceira.ConsultaDadosSolicitante;
import br.com.zup.propostas.ServicosExternos.AnaliseFinanceira.PropostaConsultaDadosResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/propostas")
public class ControllerProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaDadosSolicitante consultaDadosSolicitante;

    @Autowired
    AssociaCartaoProposta associaCartaoProposta;

    @Autowired
    private MeterRegistry meterRegistry;

    private Counter contadorPropostasCriadas;

    @PostConstruct
    public void metricas() {
        Collection<Tag> tags = new ArrayList<>();
        this.contadorPropostasCriadas = this.meterRegistry.counter("proposta_criada_com_sucesso", tags);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PropostaResponse> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) throws Exception {

        Proposta propostaSalva = propostaRepository.save(propostaRequest.toModel());

        PropostaConsultaDadosResponse propostaConsultaDadosResponse = consultaDadosSolicitante.consultaDadosSolicitante(new PropostaConsultaDadosRequest(propostaSalva));
        propostaSalva.setStatusProposta(propostaConsultaDadosResponse.retornStatusProposta());


        if (propostaSalva.getStatusProposta().equals(StatusProposta.ELEGIVEL)) {
            contadorPropostasCriadas.increment();
        }


        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(propostaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(propostaSalva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> criaPrposta(@PathVariable Long id) {

        Optional<Proposta> propostaPorId = propostaRepository.findById(id);

        return propostaPorId.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(new PropostaResponse(propostaPorId.get()));


    }


}
