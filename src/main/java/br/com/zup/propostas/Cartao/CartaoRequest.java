package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Cartao.AvisosViagem.AvisosViagem;
import br.com.zup.propostas.Cartao.AvisosViagem.AvisosViagemRequest;
import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Cartao.Bloqueio.BloqueioRequest;
import br.com.zup.propostas.Proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartaoRequest {

    @NotNull
    private String id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @NotNull
    @Positive
    private BigDecimal limite;

    @NotNull
    private RenegociacaoRequest renegociacao;

    @NotNull
    private VencimentoRequest vencimento;

    @NotNull
    private Long idProposta;

    @Deprecated
    public CartaoRequest() {
    }


    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoRequest getRenegociacao() {
        return renegociacao;
    }

    public VencimentoRequest getVencimento() {
        return vencimento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    @JsonCreator
    public CartaoRequest(String id, LocalDateTime emitidoEm, String titular,
                         BigDecimal limite, RenegociacaoRequest renegociacao, VencimentoRequest vencimento,
                         Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao toModel(Proposta proposta){


        return new Cartao(this.id,this.emitidoEm,this.titular,this.limite,this.vencimento.toModel(), proposta);

    }
}