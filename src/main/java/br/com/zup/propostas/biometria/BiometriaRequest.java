package br.com.zup.propostas.biometria;

import br.com.zup.propostas.Cartao.Cartao;
import br.com.zup.propostas.TratandoErros.ErrosDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@JsonAutoDetect
public class BiometriaRequest {

    @NotEmpty
    private Set<String> imgBiometria;

    public BiometriaRequest() {
    }


    public void setImgBiometria(Set<String> imgBiometria) {
        this.imgBiometria = imgBiometria;
    }

    public Set<String> getImgBiometria() {
        return imgBiometria;
    }

    public List<ErrosDto> verificaSeEstaBase64() {

        List<ErrosDto> errosDtos = new ArrayList<>();
        Base64.Decoder decoder = Base64.getDecoder();

        for (String e : this.imgBiometria) {

            try {

                decoder.decode(e);

            } catch (Exception ex) {

                errosDtos.add(new ErrosDto("imgBiometria", "A biometria " + e
                        + "não é em base64"));

            }

        }

        return errosDtos;
    }

    @JsonCreator
    public BiometriaRequest(Set<String> imgBiometria) {
        this.imgBiometria = imgBiometria;
    }

    public List<Biometria> toModel(Cartao cartao) {
        List<Biometria> biometrias = new ArrayList<>();

        this.imgBiometria.stream().forEach(e -> biometrias.add(new Biometria(e, cartao)));

        return biometrias;
    }
}
