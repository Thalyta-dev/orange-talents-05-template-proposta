package br.com.zup.propostas.ServicosExternos.Cartao;

import br.com.zup.propostas.Cartao.AvisosViagem.StatusAvisos;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StatusAvisosResponses {

    private StatusAvisos resultado;

    public StatusAvisosResponses(StatusAvisos resultado) {
        this.resultado = resultado;
    }

    public StatusAvisosResponses() {
    }

    @JsonFormat
    @JsonCreator
    public StatusAvisos getResultado() {
        return resultado;
    }
}
