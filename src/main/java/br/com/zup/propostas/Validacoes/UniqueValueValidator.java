package br.com.zup.propostas.Validacoes;

import com.google.common.hash.Hashing;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
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

        if (domainAttibute.equalsIgnoreCase("docHashing")) {

            o = Hashing.sha256().hashString(o.toString(), StandardCharsets.UTF_8).toString();
        }

        Query query = manager.createQuery("select 1 from " + aClass.getName() + " WHERE " + domainAttibute + " = :value");
        query.setParameter("value", o);

        List<?> lista = query.getResultList();


        Assert.isTrue(lista.size() <= 1, "Algo aconteceu e você tem um " + aClass.getName() + "com valor " + o);

        return lista.isEmpty();
    }
}
