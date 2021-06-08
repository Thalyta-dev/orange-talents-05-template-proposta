package br.com.zup.propostas.Cartao.AvisosViagem;

import br.com.zup.propostas.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class AvisosViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destino;

    @ManyToOne
    private Cartao cartao;

    private LocalDate terminoViagem;

    private String ipCliente;

    private String agentClient;

    private LocalDateTime instanteAviso = LocalDateTime.now();

    public AvisosViagem(LocalDate terminoViagem, String destino, Cartao cartao, String ipCliente, String agentClient) {
        this.destino = destino;
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.agentClient = agentClient;
        this.terminoViagem = terminoViagem;
    }

    public AvisosViagem() {
    }


}
