package br.com.zup.propostas.Cartao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao,String> {


    Optional<Cartao> findByPropostaId(String s);
}
