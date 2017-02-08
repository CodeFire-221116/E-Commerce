package ua.com.codefire.ecommerce.data.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "price_currencies")
@NamedQueries({
        @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
        @NamedQuery(name = "Currency.findNameById", query= "SELECT c.name FROM Currency c WHERE c.id = :currencyId")
})
public class Currency {
    @Id
    @Column(name = "product_currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "currency")
    private List<Product> products;

    public Currency() {
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

        Currency currency = (Currency) o;

        if (getId() != null ? !getId().equals(currency.getId()) : currency.getId() != null) return false;
        if (!getName().equals(currency.getName())) return false;
        return getProducts() != null ? getProducts().equals(currency.getProducts()) : currency.getProducts() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        return result;
    }
}
