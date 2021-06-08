package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Cartao.StatusCartao;
import br.com.zup.propostas.ServicosExternos.SistemaCartao;
import br.com.zup.propostas.ServicosExternos.SistemaResponsavelRequest;
import br.com.zup.propostas.ServicosExternos.StatusBloqueioResponse;
import br.com.zup.propostas.TratandoErros.ErrosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class ControllerBloqueio {

    @Autowired
    SistemaCartao bloquearCartao;

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("cartao/{idCartao}/bloqueio")
    public ResponseEntity<?> bloqueioCartao(@PathVariable String idCartao, HttpServletRequest request) {


        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (cartao.get().getCartaoBloqueado().equals(StatusCartao.BLOQUEADO)) {
            return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão já está bloqueado"));
        }

        StatusBloqueioResponse propostas = bloquearCartao.bloquearCartao(new SistemaResponsavelRequest("propostas"), idCartao);

        System.out.println(propostas.getResultado());
        if (propostas.getResultado().equals(StatusBloqueio.FALHA)) {

           return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão falhou ao ser bloqueado"));

        }

        String agentUser = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        Bloqueio salvaBloqueio = new Bloqueio("propostas",
                cartao.get(), ip, agentUser, false);

        cartao.get().bloqueiaCartao(salvaBloqueio);

        cartaoRepository.save(cartao.get());

        return ResponseEntity.ok().build();




    }
}
