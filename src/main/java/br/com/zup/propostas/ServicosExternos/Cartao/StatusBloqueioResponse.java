package br.com.zup.propostas.ServicosExternos.Cartao;

import br.com.zup.propostas.Cartao.Bloqueio.StatusBloqueio;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class StatusBloqueioResponse {

    @JsonProperty
   private StatusBloqueio resultado;


    public StatusBloqueio getResultado() {
        return resultado;
    }

    @JsonCreator

    public StatusBloqueioResponse(StatusBloqueio resultado) {
        this.resultado = resultado;
    }

    public void setResultado(StatusBloqueio resultado) {
        this.resultado = resultado;
    }
}
