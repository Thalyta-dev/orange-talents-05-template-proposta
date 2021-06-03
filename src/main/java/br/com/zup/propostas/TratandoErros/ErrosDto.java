package br.com.zup.propostas.TratandoErros;

public class ErrosDto {


    private String campo;
    private String erro;

    public ErrosDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
