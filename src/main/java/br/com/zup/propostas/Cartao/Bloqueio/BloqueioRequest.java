package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class BloqueioRequest {

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private Boolean ativo;

    private String ipCliente;

    private String agentClient;

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public BloqueioRequest() { }

    @JsonCreator
    public BloqueioRequest( String sistemaResponsavel, Boolean ativo, String ipCliente, String agentClient) {
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.ipCliente = ipCliente;
        this.agentClient = agentClient;
    }




    public Bloqueio toModel(Cartao cartao){

        return  new Bloqueio(sistemaResponsavel,cartao,ipCliente,agentClient,ativo);
    }

}
