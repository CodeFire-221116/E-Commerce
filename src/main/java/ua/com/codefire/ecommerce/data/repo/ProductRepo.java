package ua.com.codefire.ecommerce.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by human on 1/31/17.
 */
@Repository
@Transactional(readOnly = true)
public class ProductRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

//    public List<Integer> getAllProductsIds() {
//        return entityManager.createQuery("SELECT p.id FROM Product p", Integer.class)
//                .getResultList();
//    }

    @Transactional(readOnly = false)
    public void add(Product product) {
            entityManager.persist(product);
    }

    @Transactional
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void update(Product product){
        entityManager.merge(product);
    }

    @Transactional
    public void delete(Product product){
        entityManager.remove(product);
    }
}
