package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.entity.Price;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class PriceRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Price> findAll() {
        return entityManager.createQuery("SELECT p FROM Price p", Price.class)
                .getResultList();
    }

    public List<Integer> getAllPricesIds() {
        return entityManager.createQuery("SELECT p.id FROM Price p", Integer.class)
                .getResultList();
    }

    @Transactional
    public boolean addPrice(Price price) {
        try {
            entityManager.persist(price);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Price getPriceById(int id) {
        return entityManager.find(Price.class, id);
    }
}
