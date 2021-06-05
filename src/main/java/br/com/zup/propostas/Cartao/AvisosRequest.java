package br.com.zup.propostas.Cartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class AvisosRequest {



    private Long id;

    @FutureOrPresent
    @NotNull
    private Date validoAte;

    @NotBlank
    private String destino;

    public AvisosRequest() {
    }

    @JsonCreator
    public AvisosRequest(Long id, Date validoAte, String destino) {
        this.id = id;
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Avisos toModel(){
       return new Avisos(id,validoAte, destino);
    }
}
