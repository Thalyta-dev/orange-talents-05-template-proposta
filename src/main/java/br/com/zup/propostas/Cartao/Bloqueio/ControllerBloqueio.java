package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.ServicosExternos.ServicoCartao;
import br.com.zup.propostas.ServicosExternos.SistemaResponsavel;
import br.com.zup.propostas.TratandoErros.ErrosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerBloqueio {

    @Autowired
    ServicoCartao bloquearCartao;

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("cartao/{idCartao}/bloqueio")
    public ResponseEntity<?> boqueioCartao(@PathVariable String idCartao, HttpServletRequest request) {


        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrosDto("cartao", "Cartão não existe"));
        }


        List<Cartao> byCartaoBloqueado = cartaoRepository.findByCartaoBloqueado(idCartao);


        if (!byCartaoBloqueado.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão já está bloqueado"));
        }

        // ResponseEntity<ResultadoBloqueio> propostas = bloquearCartao.bloquearCartao(new SistemaResponsavel("propostas"), idCartao);

        String agentUser = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        // boolean resultadoBloqueio = propostas.getBody().resultadoBloqueio();

        Bloqueio salvaBloqueio = new Bloqueio("propostas",
                cartao.get(), ip, agentUser,  false);

//        if(resultadoBloqueio) {
//            cartao.get().getBloqueios().add(salvaBloqueio);
//            cartaoRepository.save(cartao.get());
//            return ResponseEntity.ok().build();
//        }

        cartao.get().getBloqueios().add(salvaBloqueio);
        cartaoRepository.save(cartao.get());
        return ResponseEntity.ok().build();


//        return ResponseEntity.badRequest().body(new ErrosDto("cartao", "não conseguimos bloquear o cartão, tente mais tarde"));


    }
}
