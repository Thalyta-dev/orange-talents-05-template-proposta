package br.com.zup.propostas.Cartao.Bloqueio;

public enum StatusBloqueio {

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
