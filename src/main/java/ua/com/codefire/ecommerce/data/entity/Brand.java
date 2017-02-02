package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "product_brands")
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")
})
public class Brand implements Serializable {

    @Id
    @Column(name = "product_brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brand brand = (Brand) o;

        return getId().equals(brand.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
