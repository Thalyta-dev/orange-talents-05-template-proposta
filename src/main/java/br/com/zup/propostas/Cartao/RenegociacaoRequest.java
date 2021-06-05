package br.com.zup.propostas.Cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class RenegociacaoRequest {



    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    private LocalDateTime dateTime;

    public RenegociacaoRequest() {
    }
    @JsonCreator
    public RenegociacaoRequest(String id, Integer quantidade, BigDecimal valor, LocalDateTime dateTime) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Renegociacao toModel(){
        return new Renegociacao(id, quantidade,valor,dateTime);
    }


}
