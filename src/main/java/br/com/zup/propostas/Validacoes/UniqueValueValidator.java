package br.com.zup.propostas.Validacoes;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttibute;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.aClass = constraintAnnotation.domainClass();
        this.domainAttibute = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        Query query = manager.createQuery("select 1 from " + aClass.getName() + " where " + domainAttibute + " =:value");
        query.setParameter("value", o);

        List<?> lista = query.getResultList();
        Assert.isTrue(lista.size() <= 1, "Algo aconteceu e você tem um " + aClass.getName() + "com valor " +o );
       return  lista.isEmpty();
    }
}
