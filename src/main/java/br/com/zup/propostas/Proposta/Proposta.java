package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.Validacoes.CnpjOrCpf;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private  StatusProposta statusProposta;

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public String getDocumento() {
        return documento;
    }

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
