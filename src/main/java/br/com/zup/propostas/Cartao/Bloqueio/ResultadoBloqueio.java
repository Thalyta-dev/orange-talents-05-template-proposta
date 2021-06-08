package br.com.zup.propostas.Cartao.Bloqueio;

public enum ResultadoBloqueio {

    BLOQUEADO{
        boolean resultadoBloqueio(){
            return true;
        }

    }, FALHA{

        boolean resultadoBloqueio(){
            return false;
        }
    };

    abstract boolean resultadoBloqueio();
}
