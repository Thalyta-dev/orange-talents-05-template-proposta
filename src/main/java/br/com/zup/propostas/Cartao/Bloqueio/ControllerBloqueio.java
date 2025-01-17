package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Cartao.StatusCartao;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaCartao;
import br.com.zup.propostas.ServicosExternos.Cartao.SistemaResponsavelRequest;
import br.com.zup.propostas.ServicosExternos.Cartao.StatusBloqueioResponse;
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
    public ResponseEntity<?> bloqueioCartao(@PathVariable  String idCartao, HttpServletRequest request) {


        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (cartao.get().getCartaoBloqueado().equals(StatusCartao.BLOQUEADO)) {
            return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão  está bloqueado"));
        }

        if (cartao.get().getCartaoBloqueado().equals(StatusCartao.BLOQUEADO)) {
            return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão já está bloqueado"));
        }

        StatusBloqueioResponse statusBloqueioCartao = bloquearCartao.bloquearCartao(new SistemaResponsavelRequest("propostas"), idCartao);

        if (statusBloqueioCartao.getResultado().equals(StatusBloqueio.FALHA)) {

           return ResponseEntity.unprocessableEntity().body(new ErrosDto("cartao", "Cartão falhou ao ser bloqueado"));

        }

        String agentUser = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        BloqueioRequest salvaBloqueio = new BloqueioRequest("propostas", false,ip, agentUser);

        Bloqueio bloqueio = salvaBloqueio.toModel(cartao.get());

        cartao.get().bloqueiaCartao(bloqueio);

        cartaoRepository.save(cartao.get());

        return ResponseEntity.ok().build();




    }
}
