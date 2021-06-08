package br.com.zup.propostas.Cartao;

import br.com.zup.propostas.Cartao.Bloqueio.Bloqueio;
import br.com.zup.propostas.Proposta.Proposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaoRepository extends CrudRepository<Cartao,String> {

    @Query(value = "SELECT * FROM  cartao as c inner join bloqueio as b on c.id = b.cartao_id  where  cartao_id= :idCartao and ativo = 0", nativeQuery = true)
    List<Cartao> findByCartaoBloqueado(String idCartao);
}
