package br.com.zup.propostas.ServicosExternos.AnaliseFinanceira;

import br.com.zup.propostas.Proposta.StatusProposta;

public enum PropostaRetorno {

        COM_RESTRICAO{

            StatusProposta retorno(){
                return StatusProposta.NAO_ELEGIVEL;
            }

        },
        SEM_RESTRICAO{

            StatusProposta retorno(){
                return StatusProposta.ELEGIVEL;
            }
        };

        abstract StatusProposta retorno();
}
