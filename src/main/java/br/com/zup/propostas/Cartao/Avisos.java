package br.com.zup.propostas.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Avisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date validoAte;

    private String destino;

    public Avisos(Long id, Date validoAte, String destino) {
        this.id = id;
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Avisos() {
    }

    public Avisos(Date validoAte, String destino) {
        this.validoAte = validoAte;
        destino = destino;
    }
}
