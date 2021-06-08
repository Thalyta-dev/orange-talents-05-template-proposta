package br.com.zup.propostas.Cartao.AvisosViagem;

import br.com.zup.propostas.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;


public class AvisosViagemRequest {


    @FutureOrPresent
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    @NotBlank
    @NotNull
    private String destino;

    public AvisosViagemRequest() {
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public AvisosViagem toModel(Cartao cartao, String ipCliente, String agentClient){

        return new AvisosViagem(validoAte,destino,cartao,ipCliente,agentClient);
    }
}
