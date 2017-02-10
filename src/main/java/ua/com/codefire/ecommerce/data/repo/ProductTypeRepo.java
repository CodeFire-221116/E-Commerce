package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class ProductTypeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ProductType> findAll() {
        return entityManager.createNamedQuery("ProductType.findAll", ProductType.class).getResultList();
    }

    @Transactional(readOnly = false)
    public void add(ProductType productType) {
        entityManager.persist(productType);
    }

    @Transactional
    public ProductType getById(int id) {
        return entityManager.find(ProductType.class, id);
    }

    @Transactional
    public String getNameById(int id) {
        return entityManager.createNamedQuery("ProductType.findNameById").setParameter("productTypeId", id).getSingleResult().toString();
    }

    @Transactional
    public void update(ProductType type){
        entityManager.merge(type);
    }

    @Transactional
    public void delete(ProductType type){
        entityManager.remove(type);
    }
}
