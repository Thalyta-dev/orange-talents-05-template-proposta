package br.com.zup.propostas.Proposta;

public class PropostaResponse {

    private Long id;
    private String nome;
    private String email;
    private StatusProposta statusProposta;


    public Long getId() {
        return id;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
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

        this.statusProposta = propostaSalva.getStatusProposta();

    }
}
