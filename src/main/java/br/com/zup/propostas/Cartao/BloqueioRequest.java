package br.com.zup.propostas.Cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class BloqueioRequest {


    private String id;


    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;


    private Boolean ativo;

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }


    public BloqueioRequest(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public BloqueioRequest() { }

    @JsonCreator
    public BloqueioRequest(LocalDateTime dataBloqueio, String sistemaResponsavel, Boolean ativo) {
        this.bloqueadoEm = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio toModel(){
        return  new Bloqueio(id,this.bloqueadoEm,sistemaResponsavel,ativo);
    }

}
