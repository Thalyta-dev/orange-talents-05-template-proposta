package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Carteiras {

    @Id
    private String id;

    private String email;

    private LocalDateTime associadaEm;

    private String emissor;

    public Carteiras(String email, LocalDateTime associadaEm, String emissor) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteiras(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteiras() {
    }
}
