package br.com.zup.propostas.TratandoErros;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class Handler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException exception) {

       boolean contemErroValidadorUnico = false;

        List<ErrosDto> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrosDto erro = new ErrosDto(e.getField(), mensagem);

            dto.add(erro);
        });

        boolean naoContemErro = dto.stream().filter(e ->
                e.getErro().equalsIgnoreCase("Já existe um documento com essa proposta"))
                .collect(Collectors.toList()).isEmpty();

        if (!naoContemErro) {
                return ResponseEntity.unprocessableEntity().body(dto);

        }

        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(FeignException.class)
    public String handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return "feignError: "+ e.getMessage();
    }

}
