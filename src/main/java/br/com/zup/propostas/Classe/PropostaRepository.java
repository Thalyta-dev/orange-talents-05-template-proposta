package br.com.zup.propostas.Classe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta,Long> {
    Optional<Proposta> findByDocumento(String documento);;
    @Query(value = "SELECT LAST_INSERT_ID() FROM proposta as p", nativeQuery = true)
    Long getLastId();


}
