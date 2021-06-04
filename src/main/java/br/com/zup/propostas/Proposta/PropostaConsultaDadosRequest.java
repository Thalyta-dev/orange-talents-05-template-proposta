package br.com.zup.propostas.Proposta;

public class PropostaConsultaDadosRequest {

    private Long idProposta;

    private String nome;

    private String documento;

    public Long getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public PropostaConsultaDadosRequest(Proposta proposta)   {
        this.idProposta = proposta.getId();
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
    }
}
