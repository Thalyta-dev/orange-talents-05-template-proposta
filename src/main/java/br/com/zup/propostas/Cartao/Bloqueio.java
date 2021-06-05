package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    private String id;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;


    private Boolean ativo;

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio() { }

    public Bloqueio(LocalDateTime dataBloqueio, String sistemaResponsavel, Boolean ativo) {
        this.bloqueadoEm = dataBloqueio;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }


}
