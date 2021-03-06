package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Currency;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class CurrencyRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Currency> findAll() {
        return entityManager.createNamedQuery("Currency.findAll", Currency.class)
                .getResultList();
    }

    @Transactional(readOnly = false)
    public void add(Currency currency) {
        entityManager.persist(currency);
    }

    public Currency getById(int id) {
        return entityManager.find(Currency.class, id);
    }
}
