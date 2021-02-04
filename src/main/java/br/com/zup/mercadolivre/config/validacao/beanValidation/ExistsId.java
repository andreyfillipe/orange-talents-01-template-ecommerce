package br.com.zup.mercadolivre.config.validacao.beanValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistsIdValidator.class)
public @interface ExistsId {

    String message() default "não existe com o id informado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String nomeCampo();

    Class<?> nomeClasse();
}
