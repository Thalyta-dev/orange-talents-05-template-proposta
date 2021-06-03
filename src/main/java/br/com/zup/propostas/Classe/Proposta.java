package br.com.zup.propostas.Classe;

import br.com.zup.propostas.Validacoes.CnpjOrCpf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue
    private Long id;


    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CnpjOrCpf
    private String documento;

    @NotBlank
    private String endereco;

    @Positive
    @NotNull
    private BigDecimal salario;

    public Proposta(String nome, String email,
                    String documento, String endereco,
                    BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Deprecated
    public Proposta() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
