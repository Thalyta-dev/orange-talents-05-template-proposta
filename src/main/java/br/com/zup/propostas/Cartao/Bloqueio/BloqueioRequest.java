package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class BloqueioRequest {

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;


    private Boolean ativo;

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public BloqueioRequest() { }

    @JsonCreator
    public BloqueioRequest(LocalDateTime dataBloqueio, String sistemaResponsavel, Boolean ativo) {
        this.bloqueadoEm = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio toModel(){
        return  new Bloqueio(this.bloqueadoEm,sistemaResponsavel,ativo);
    }

}
