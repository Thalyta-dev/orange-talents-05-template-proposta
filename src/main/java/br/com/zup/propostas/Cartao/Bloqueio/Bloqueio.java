package br.com.zup.propostas.Cartao.Bloqueio;

import br.com.zup.propostas.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bloqueio;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    @ManyToOne
    private Cartao cartao;

    private String ipCliente;

    private String agentClient;

    private Boolean ativo;

    public Bloqueio(String sistemaResponsavel, Cartao cartao, String ipCliente, String agentClient, Boolean ativo) {
        this.bloqueadoEm = LocalDateTime.now();
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.agentClient = agentClient;
        this.ativo = ativo;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public Bloqueio( LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio() { }



}
