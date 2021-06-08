package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.Cartao.biometria.Biometria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao")
    private List<Bloqueio> bloqueios;

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Avisos> avisos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Carteiras> carteiras;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parcelas> parcelas;

    private BigDecimal limite;

    @ManyToOne(cascade = CascadeType.ALL)
    private Renegociacao renegociacao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias;

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public String getId() {
        return id;
    }

    public void setBiometrias(List<Biometria> biometrias) {
        this.biometrias = biometrias;
    }


    @Deprecated
    public Cartao() {
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }


    public Cartao(String id, LocalDateTime emitidoEm, String titularCartao, List<Bloqueio> bloqueio, List<Avisos> avisos, List<Carteiras> carteiras, List<Parcelas> parcelas, BigDecimal limite,
                  Renegociacao renegociacao, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titularCartao;
        this.bloqueios = bloqueio;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public  Cartao associaBiometriaAoCartão(List<Biometria> biometrias){
         this.biometrias.addAll(biometrias);
         return this;

    }
}
