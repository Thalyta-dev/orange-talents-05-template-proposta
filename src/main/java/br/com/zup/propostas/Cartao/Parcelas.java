package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Parcelas {

    @Id
    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    public Parcelas(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Parcelas() {
    }

    public Parcelas(Integer quantidade, BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
