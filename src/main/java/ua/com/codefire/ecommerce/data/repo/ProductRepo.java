package ua.com.codefire.ecommerce.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Brand;
import ua.com.codefire.ecommerce.data.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by human on 1/31/17.
 */
@Repository
@Transactional(readOnly = true)
public class ProductRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public List<Integer> getAllProductsIds() {
        return entityManager.createQuery("SELECT p.id FROM Product p", Integer.class)
                .getResultList();
    }

    @Transactional
    public boolean addProduct(Product product) {
        try {
            entityManager.persist(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);
    }
}
