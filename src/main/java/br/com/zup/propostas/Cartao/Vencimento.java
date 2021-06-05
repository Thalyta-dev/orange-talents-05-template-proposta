package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    private String id;

    private Integer dia;

    private LocalDateTime dataCriacao;

    public Vencimento() {
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getId() {
        return id;
    }

    public Vencimento(String id, Integer dia, LocalDateTime dataCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }
}
