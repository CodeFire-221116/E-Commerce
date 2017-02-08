package ua.com.codefire.ecommerce.data.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "product_brands")
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b"),
        @NamedQuery(name = "Brand.findNameById", query= "SELECT b.name FROM Brand b WHERE b.id = :brandId")
})
public class Brand implements Serializable {

    @Id
    @Column(name = "product_brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        if (getId() != null ? !getId().equals(brand.getId()) : brand.getId() != null) return false;
        if (!getName().equals(brand.getName())) return false;
        return getProducts() != null ? getProducts().equals(brand.getProducts()) : brand.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }
}
