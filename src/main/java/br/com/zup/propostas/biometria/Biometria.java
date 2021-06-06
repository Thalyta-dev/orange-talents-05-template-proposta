package br.com.zup.propostas.biometria;

import br.com.zup.propostas.Cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "text")
    private String imgBiometria;

    @ManyToOne
    private Cartao cartao;

    public Biometria() {
    }


    public Biometria(String imgBiometria, Cartao cartao) {
        this.imgBiometria = imgBiometria;
        this.cartao = cartao;
    }

    public Biometria(String imgBiometria) {
        this.imgBiometria = imgBiometria;
    }
}
