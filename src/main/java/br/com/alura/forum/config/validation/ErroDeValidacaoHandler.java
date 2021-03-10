package br.com.alura.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    private final MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ErroForm> handle(MethodArgumentNotValidException e) {
        List<ErroForm> list = new ArrayList<>();
        List<FieldError> campos = e.getBindingResult().getFieldErrors();
        campos.forEach(c -> {
            String mensagem = messageSource.getMessage(c, LocaleContextHolder.getLocale());
            ErroForm erroForm = new ErroForm(c.getField(), mensagem);

            list.add(erroForm);
        });

        return list;
    }
}
