package br.com.zup.propostas.Cartao.Carteira;

import br.com.zup.propostas.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteiras {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCarteiras carteiras;

    @ManyToOne
    private Cartao cartao;

    private String idSistemaLegado;

    public TipoCarteiras getCarteiras() {
        return carteiras;
    }

    public Carteiras(String email, TipoCarteiras carteiras, Cartao cartao, String idSistemaLegado) {
        this.email = email;
        this.carteiras = carteiras;
        this.cartao = cartao;
        this.idSistemaLegado = idSistemaLegado;
    }

    public Long getId() {
        return id;
    }

    public Carteiras() {
    }
}
