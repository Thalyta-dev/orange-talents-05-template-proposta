
package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Cartao.AvisosViagem.AvisosViagem;
import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Cartao.Carteira.Carteiras;
import br.com.zup.propostas.Cartao.Carteira.TipoCarteiras;
import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.Cartao.biometria.Biometria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao")
    private List<Bloqueio> bloqueios;

    @Enumerated(EnumType.STRING)
    private StatusCartao cartaoBloqueado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao")
    private List<AvisosViagem> avisos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao")
    private List<Carteiras> carteiras;

    private BigDecimal limite;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias;

    @Deprecated
    public Cartao() {
    }


    public List<AvisosViagem> getAvisos() {
        return avisos;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public StatusCartao getCartaoBloqueado() {
        return cartaoBloqueado;
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite, Vencimento vencimento, Proposta proposta) {
        this.cartaoBloqueado = StatusCartao.DESBLOQUEADO;
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public Cartao associaBiometriaAoCartao(List<Biometria> biometrias) {
        this.biometrias.addAll(biometrias);
        return this;

    }

    public void bloqueiaCartao(Bloqueio bloqueio) {

        this.cartaoBloqueado = StatusCartao.BLOQUEADO;

        this.bloqueios.add(bloqueio);
    }

    public void associaAvisosCartao(AvisosViagem avisosViagem) {
        avisos.add(avisosViagem);

    }

    public List<Carteiras> getCarteiras() {
        return carteiras;
    }

    public boolean verificaSeACarteiraJaEstaAssociada(TipoCarteiras carteiras){
        List<Carteiras> carteirasAssociada = this.getCarteiras().stream().filter(e -> e.getCarteiras().equals(carteiras)).collect(Collectors.toList());

        return carteirasAssociada.isEmpty();
    }

    public void associaCarteiraAoCartao(Carteiras carteira) {

        this.carteiras.add(carteira);
    }
}
