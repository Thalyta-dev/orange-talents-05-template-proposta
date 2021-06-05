package br.com.zup.propostas.Proposta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta,Long> {


    Optional<Proposta> findByDocumento(String documento);;

    @Query(value = "SELECT * FROM  proposta as p left join cartao as c " +
            "on p.id = c.proposta_id  where p.status_proposta = 'ELEGIVEL' and c.proposta_id  is null", nativeQuery = true)
    List<Proposta> findByStatusPropostaElegivelSemCartao();

}
