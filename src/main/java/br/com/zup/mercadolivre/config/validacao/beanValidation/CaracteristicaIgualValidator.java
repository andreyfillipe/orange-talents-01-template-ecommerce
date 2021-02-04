package br.com.zup.mercadolivre.config.validacao.beanValidation;

import br.com.zup.mercadolivre.produto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class CaracteristicaIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        ProdutoRequest request = (ProdutoRequest) o;
        Set<String> nomesRepetidos = request.buscarCaracteristicaIgual();
        if (!nomesRepetidos.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Existem Características repetidas: " + nomesRepetidos);
        }
    }
}
