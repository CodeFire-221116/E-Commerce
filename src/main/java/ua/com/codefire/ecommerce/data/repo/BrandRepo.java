package ua.com.codefire.ecommerce.data.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.ecommerce.data.entity.Brand;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class BrandRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Brand> findAll() {
        return entityManager.createNamedQuery("Brand.findAll", Brand.class)
                .getResultList();
    }

    @Transactional(readOnly = false)
    public void addBrand(Brand brand) {
        entityManager.persist(brand);
    }


    @Transactional
    public Brand getById(int id) {
        return entityManager.find(Brand.class, id);
    }
}
