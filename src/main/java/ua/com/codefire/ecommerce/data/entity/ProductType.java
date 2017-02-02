package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by human on 1/31/17.
 */
@Entity
@Table(name = "product_types")
public class ProductType implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "productType")
    private List<Product> products;

    public ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        ProductType that = (ProductType) o;

        return getId() == that.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }
}
