package br.com.zup.propostas.Cartao.Carteira;

import br.com.zup.propostas.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarteirasRequest {

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private TipoCarteiras carteira;


    public CarteirasRequest() {
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiras getCarteira() {
        return carteira;
    }

    public CarteirasRequest(String email, TipoCarteiras carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public Carteiras toModel(Cartao cartao, String idSistemaLegado){
        return new Carteiras(email,carteira,cartao,idSistemaLegado);
    }
}
