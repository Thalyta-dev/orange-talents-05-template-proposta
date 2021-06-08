package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Proposta.Proposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaoRepository extends CrudRepository<Cartao,String> {

}
