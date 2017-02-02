package ua.com.codefire.ecommerce.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ankys on 02.02.2017.
 */
@Entity
@Table(name = "product_prices")
@NamedQueries({
        @NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p")
})
public class Price {
    @Id
    @Column(name = "product_price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Double value;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;
    @OneToMany(mappedBy = "price")
    private List<Product> product;
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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (id != price.id) return false;
        if (!value.equals(price.value)) return false;
        if (!lastUpdated.equals(price.lastUpdated)) return false;
        return currency != null ? currency.equals(price.currency) : price.currency == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value.hashCode();
        result = 31 * result + lastUpdated.hashCode();
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
