package br.com.zup.propostas.ServicosExternos.Cartao;

import br.com.zup.propostas.Cartao.Carteira.StatusCarteira;
import com.fasterxml.jackson.annotation.JsonCreator;

public class StatusCarteiraResponse {

    private StatusCarteira resultado;

    private String id;

    @JsonCreator
    public StatusCarteiraResponse(StatusCarteira resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusCarteira getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
