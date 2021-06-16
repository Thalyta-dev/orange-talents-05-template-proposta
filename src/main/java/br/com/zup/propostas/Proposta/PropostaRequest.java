package br.com.zup.propostas.Proposta;

import br.com.zup.propostas.Validacoes.CnpjOrCpf;
import br.com.zup.propostas.Validacoes.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class PropostaRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @CnpjOrCpf
    @UniqueValue(domainClass = Proposta.class, fieldName = "docHashing", message = "Já existe um documento com essa proposta")
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
        String codificaoDocumento =
                Encryptors.text("password", "5c0744940b5c369b").encrypt(this.documento);

        String docHashing = Hashing.sha256().hashString(this.documento, StandardCharsets.UTF_8).toString();

        return  new Proposta(this.nome,this.email, codificaoDocumento, docHashing, this.endereco, this.salario);
    }
}
