package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.entity.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return entityManager.createQuery("SELECT pt FROM ProductType pt", ProductType.class)
                .getResultList();
    }

    @Transactional(readOnly = false)
    public void add(ProductType productType) {
        entityManager.persist(productType);
    }

    public ProductType getProductTypeById(int id) {
        return entityManager.find(ProductType.class, id);
    }

    @Transactional
    public List<Product> findProductsByTypeId(int id) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.productType.id = :pt_id");
        query.setParameter("pt_id", id);
        return query.getResultList();
    }
}
