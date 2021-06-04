package br.com.zup.propostas.ServicosExternos;

import br.com.zup.propostas.Proposta.StatusProposta;

public class PropostaConsultaDadosResponse {

    private Long idProposta;

    private String nome;

    private String documento;

    private PropostaRetorno resultadoSolicitacao;

    public PropostaConsultaDadosResponse(Long idProposta, String nome,
                                         String documento, PropostaRetorno resultadoSolicitacao) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public StatusProposta retornStatusProposta(){
       return this.resultadoSolicitacao.retorno();
    }

    public PropostaRetorno getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
