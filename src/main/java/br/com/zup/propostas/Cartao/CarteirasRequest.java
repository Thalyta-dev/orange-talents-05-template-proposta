package br.com.zup.propostas.Cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

public class CarteirasRequest {


    private String id;

    @Email
    private String email;

    private LocalDateTime associadaEm;

    private String emissor;

    @JsonCreator
    public CarteirasRequest(String email, LocalDateTime associadaEm, String emissor) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    public Carteiras toModel(){
        return new Carteiras(id,email,associadaEm,emissor);
    }
    public CarteirasRequest() {
    }
}
