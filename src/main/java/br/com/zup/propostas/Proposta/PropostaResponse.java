package br.com.zup.propostas.Proposta;

public class PropostaResponse {

    private Long id;
    private String nome;
    private String email;


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public PropostaResponse(Proposta propostaSalva) {

        this.id = propostaSalva.getId();

        this.nome = propostaSalva.getNome();

        this.email = propostaSalva.getEmail();

    }
}
