package br.com.zup.mercadolivre.config.validacao.beanValidation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String nomeCampo;
    private Class<?> nomeClasse;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.nomeCampo = constraintAnnotation.nomeCampo();
        this.nomeClasse = constraintAnnotation.nomeClasse();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + this.nomeClasse.getName() + " where " + this.nomeCampo + " = :value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        return list.isEmpty();
    }
}
