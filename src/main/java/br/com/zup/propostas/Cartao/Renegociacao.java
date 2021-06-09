package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    private String id;

    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private BigDecimal valor;

    private LocalDateTime dateTime;

    public Renegociacao() {
    }

    public Renegociacao(Integer quantidade, BigDecimal valor, LocalDateTime dateTime) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.dateTime = dateTime;
    }
    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dateTime) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }
}
