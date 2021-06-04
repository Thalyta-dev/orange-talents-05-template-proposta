package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.Validacoes.CnpjOrCpf;
import br.com.zup.propostas.Validacoes.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @CnpjOrCpf
    @UniqueValue(domainClass = Proposta.class, fieldName = "documento", message = "Já existe um documento com essa proposta")
    private String documento;

    @NotEmpty
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;

    @JsonCreator
    public PropostaRequest(String nome, String email, String documento, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel(){
        return  new Proposta(this.nome,this.email, this.documento, this.endereco, this.salario);
    }
}
