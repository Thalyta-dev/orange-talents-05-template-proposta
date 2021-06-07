package br.com.zup.propostas;

import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.Proposta.PropostaRepository;
import br.com.zup.propostas.Proposta.PropostaRequest;
import br.com.zup.propostas.Proposta.PropostaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureDataJpa
class PropostasApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PropostaRepository propostaRepository;


	@Test
	@Transactional
	void deveRetornarPropostaSalva() throws Exception {

		PropostaRequest propostaRequest = new PropostaRequest("Thalyta", "thalyta@gmail.com", "124.853.036-57",
				"Travessa vicente de paula pedroso",new BigDecimal("77777.0"));


		String json = CriacaoJson(propostaRequest);

		Proposta proposta = propostaRequest.toModel();



		mockMvc.perform(post("/propostas").contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());


		Optional<Proposta> propostaByDocumento = propostaRepository.findByDocumento("124.853.036-57");


		Assertions.assertAll(
				() -> Assertions.assertEquals(propostaByDocumento.get().getNome(), "Thalyta"),
				() -> Assertions.assertEquals(propostaByDocumento.get().getEmail(), "thalyta@gmail.com")

		);

	}

	@Test
	@Transactional
	void naoDeveCadastrarPropostaPoisDocumentoInvalidoESalarioNegativo() throws Exception {

		PropostaRequest propostaRequest = new PropostaRequest("Thalyta", "thalyta@gmail.com", "124.853.036-576",
				"Travessa vicente de paula pedroso",new BigDecimal("-77777.0"));

		String json = CriacaoJson(propostaRequest);

		String jsonResult = CriacaoJson(new PropostaResponse(propostaRequest.toModel()));

		mockMvc.perform(post("/propostas").contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest());


		Optional<Proposta> propostaByDocumento = propostaRepository.findByDocumento("124.853.036-57");



	}



	private String CriacaoJson(Object autorRequest) throws JsonProcessingException {
		return objectMapper.writeValueAsString(autorRequest);
	}


}
