package br.com.zup.propostas.Cartao.AvisosViagem;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.Cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("cartao/{idCartao}/avisoViagem")
    public ResponseEntity cadastraAvisoViagem(@RequestBody @Valid AvisosViagemRequest avisosViagemRequest,
                                              @PathVariable String idCartao, HttpServletRequest request) {

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String agentUser = request.getHeader("User-Agent");
        String ip = request.getRemoteAddr();


        AvisosViagem avisosViagem = avisosViagemRequest.toModel(cartao.get(), ip, agentUser);
        cartao.get().associaAvisosCartao(avisosViagem);
        cartaoRepository.save(cartao.get());

        return ResponseEntity.ok().build();


    }
}
