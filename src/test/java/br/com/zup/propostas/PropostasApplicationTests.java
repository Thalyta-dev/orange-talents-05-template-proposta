package br.com.zup.propostas;

import br.com.zup.propostas.Cartao.CartaoRepository;
import br.com.zup.propostas.Proposta.Proposta;
import br.com.zup.propostas.Proposta.PropostaRepository;
import br.com.zup.propostas.Proposta.PropostaRequest;
import br.com.zup.propostas.Proposta.PropostaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@AutoConfigureDataJpa
@SpringBootTest
class PropostasApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PropostaRepository propostaRepository;

	@Autowired
	CartaoRepository cartaoRepository;

	@Value( value = "${jwt.test}")
	String token;

	@Test
	void deveRetornarPropostaSalva() throws Exception {



		PropostaRequest propostaRequest = new PropostaRequest("Thalyta", "thalyta@gmail.com", "124.853.036-57",
				"Travessa vicente de paula pedroso",new BigDecimal("77777.0"));


		String json = CriacaoJson(propostaRequest);

		Proposta proposta = propostaRequest.toModel();


		mockMvc.perform(post("/propostas").contentType(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("SCOPE_propostas")))
				.content(json))
				.andExpect(status().isCreated());


		Optional<Proposta> propostaByDocumento = propostaRepository.findByDocumento(proposta.getDocumento());


		Assertions.assertAll(
				() -> Assertions.assertEquals(propostaByDocumento.get().getNome(), "Thalyta"),
				() -> Assertions.assertEquals(propostaByDocumento.get().getEmail(), "thalyta@gmail.com")

		);

	}

	@Test
	void naoDeveCadastrarPropostaPoisDocumentoInvalidoESalarioNegativo() throws Exception {

		PropostaRequest propostaRequest = new PropostaRequest("Thalyta", "thalyta@gmail.com", "899.353.880-85",
				"Travessa vicente de paula pedroso",new BigDecimal("-77777.0"));

		String json = CriacaoJson(propostaRequest);



		mockMvc.perform(post("/propostas").contentType(MediaType.APPLICATION_JSON)
				.with(SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("SCOPE_propostas")))
				.content(json))
		.andExpect(status().isBadRequest());

	}




		private String CriacaoJson(Object autorRequest) throws JsonProcessingException {
		return objectMapper.writeValueAsString(autorRequest);
	}


}
