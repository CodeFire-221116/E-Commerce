package ua.com.codefire.ecommerce.data.entity;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "product_prices")
@NamedQueries({
        @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"),
        @NamedQuery(name = "Price.findValueById", query= "SELECT p.value FROM Price p WHERE p.id = :priceId"),
        @NamedQuery(name = "Price.findTopical", query = "SELECT p FROM Price p WHERE p.isTopical = true AND p.product.id = :productId")
})
public class Price {
    @Id
    @Column(name = "product_price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="price_value")
    private Double value;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "is_topical")
    private boolean isTopical;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "price_currency_id")
    private Currency currency;

    public Price() {
    }

    public Price(Double value, Timestamp lastUpdated, Currency currency) {
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ua.com.codefire.ecommerce.data.entity.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(ua.com.codefire.ecommerce.data.entity.Currency currency) {
        this.currency = currency;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isTopical() {
        return isTopical;
    }

    public void setTopical(boolean topical) {
        isTopical = topical;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (id != price.id) return false;
        if (value != null ? !value.equals(price.value) : price.value != null) return false;
        return lastUpdated != null ? lastUpdated.equals(price.lastUpdated) : price.lastUpdated == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
