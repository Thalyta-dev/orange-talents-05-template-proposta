package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.Proposta.PropostaRepository;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartaoRequest {

    @NotNull
    private String id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    private List<BloqueioRequest> bloqueios;

    private List<AvisosRequest> avisos;

    private List<CarteirasRequest> carteiras;

    private List<ParcelasRequest> parcelas;

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


    @JsonCreator
    public CartaoRequest(String id, LocalDateTime emitidoEm, String titular, List<BloqueioRequest> bloqueios, List<AvisosRequest> avisos, List<CarteirasRequest> carteiras, List<ParcelasRequest> parcelas,
                         BigDecimal limite, RenegociacaoRequest renegociacao, VencimentoRequest vencimento,
                         Long idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
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

    public List<BloqueioRequest> getBloqueios() {
        return bloqueios;
    }

    public List<AvisosRequest> getAvisos() {
        return avisos;
    }

    public List<CarteirasRequest> getCarteiras() {
        return carteiras;
    }

    public List<ParcelasRequest> getParcelas() {
        return parcelas;
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

    public Cartao toModel(Proposta proposta){

        List<Bloqueio> bloqueios = this.bloqueios.stream().map(BloqueioRequest::toModel).collect(Collectors.toList());
        List<Avisos> avisos = this.avisos.stream().map(AvisosRequest::toModel).collect(Collectors.toList());
        List<Carteiras> carteiras = this.carteiras.stream().map(CarteirasRequest::toModel).collect(Collectors.toList());
        List<Parcelas> parcelas = this.parcelas.stream().map(ParcelasRequest::toModel).collect(Collectors.toList());

        if (this.renegociacao != null){
            Renegociacao renegociacao = this.renegociacao.toModel();
            return new Cartao(this.id,this.emitidoEm,this.titular, bloqueios, avisos,carteiras,
                    parcelas, this.limite, renegociacao, this.vencimento.toModel(), proposta);
        }

        return new Cartao(this.id,this.emitidoEm,this.titular, bloqueios, avisos,carteiras,
                    parcelas, this.limite, this.renegociacao != null ? renegociacao.toModel(): null, this.vencimento.toModel(), proposta);

    }
}
