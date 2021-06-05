package br.com.zup.propostas.Cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class ParcelasRequest {


    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public ParcelasRequest() {
    }

    public String getId() {
        return id;
    }

    @JsonCreator
    public ParcelasRequest(Integer quantidade, BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcelas toModel(){
        return new Parcelas(id,quantidade,valor);
    }
}
